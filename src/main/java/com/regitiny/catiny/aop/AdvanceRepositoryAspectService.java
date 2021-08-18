package com.regitiny.catiny.aop;

import com.google.gson.Gson;
import com.regitiny.catiny.advance.service.impl.BaseInfoAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.HistoryUpdateAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.MasterUserAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.PermissionAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.util.MasterUserUtil;
import com.regitiny.catiny.util.ReflectUtil;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Aspect for AdvanceRepository.
 */
@Aspect
@Order(2)
@Component
@Transactional
@RequiredArgsConstructor
public class AdvanceRepositoryAspectService
{
  private final Environment env;
  private final ApplicationContext applicationContext;
  private final BaseInfoAdvanceServiceImpl baseInfoAdvanceService;
  private final PermissionAdvanceServiceImpl permissionAdvanceService;
  private final MasterUserAdvanceServiceImpl masterUserAdvanceService;
  private final HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService;

  /**
   * Retrieves the {@link Logger} associated to the given {@link JoinPoint}.
   *
   * @param joinPoint join point we want the logger for.
   * @return {@link Logger} associated to the given {@link JoinPoint}.
   */
  private Logger logger(JoinPoint joinPoint)
  {
    return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
  }


  /**
   * quản lý tự động kiểm tra phân quyền thao tác với dữ liệu
   *
   * @param joinPoint join point
   * @return result of joinPoint.proceed
   * @throws Throwable err
   */
  @Around(" execution(* com.regitiny.catiny.advance.repository.*AdvanceRepository.save(..)) && " +
    " !(" +
    " execution(* com.regitiny.catiny.*.BaseInfo*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny.*.Permission*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny.*.HistoryUpdate*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny.*.ClassInfo*Repository.save(..)) " +
    ")")
  public Object aroundPermissionCheck(ProceedingJoinPoint joinPoint) throws Throwable
  {
    var log = logger(joinPoint);
    var permissionNeedCheck = env.getProperty("catiny.permission.need-check", Boolean.class, true);
    if (Boolean.FALSE.equals(permissionNeedCheck))
      return joinPoint.proceed();

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    try
    {
      var advanceRepositoryName = Arrays.stream(joinPoint.getTarget().getClass().getInterfaces()).map(Class::getName)
        .filter(className -> className.matches("com.regitiny.catiny.advance.repository.[\\w]+AdvanceRepository"))
        .map(s -> StringUtils.remove(s, "com.regitiny.catiny.advance.repository."))
        .map(StringUtils::uncapitalize)
        .collect(Collectors.toList());

      if (advanceRepositoryName.size() != 1 || joinPoint.getArgs().length != 1)
        return joinPoint.proceed();
      var entity = joinPoint.getArgs()[0];
      log.debug("entity original. entity : {}", entity);

      var advanceRepositoryBean = joinPoint.getTarget();
      @SuppressWarnings("unchecked")
      var advanceMapperBean = (EntityAdvanceMapper<?, ?, Object>) applicationContext.getBean(advanceRepositoryName.get(0).replace("AdvanceRepository", "AdvanceMapperImpl"));
      var advanceRepositoryBeanInvoker = new ReflectUtil(advanceRepositoryBean);

      var entityOld = ReflectUtil.methodInvoke(entity, "getId")
        .map(id -> advanceRepositoryBeanInvoker.methodInvoke("findById", new Class[]{Object.class}, id).getOrNull())
        .orElse(ReflectUtil.methodInvoke(entity, "getUuid")
          .map(uuid -> advanceRepositoryBeanInvoker.methodInvoke("findOneByUuid", uuid).getOrNull()));
      //kiểm tra quyền ghi

      if (entityOld.isDefined() || entityOld.isEmpty())
      {
        if (advanceRepositoryName.get(0).toLowerCase().contains("MasterUser".toLowerCase()))
          return joinPoint.proceed();
        ReflectUtil.methodInvoke(entity, "setInfo", baseInfoAdvanceService.createForOwner());
      }
      else
      {
        var baseInfoOption = ReflectUtil.methodInvoke(entityOld.get(), "getInfo")
          .map(BaseInfo.class::cast);
        var canUpdate = baseInfoOption
          .map(baseInfo -> Option.of(baseInfo.getOwner())
            .map(MasterUser::getId)
            .map(id -> id.equals(MasterUserUtil.getCurrentMasterUser().map(MasterUser::getId).get()))
            .orElse(permissionAdvanceService.publicLocal().advanceRepository
              .findOneByBaseInfoAndOwner(baseInfo, MasterUserUtil.getCurrentMasterUser().getOrElse(MasterUserUtil.anonymousMasterUser().get()))
              .map(Permission::getWrite))
            .get())
          .getOrElse(false);
        if (Boolean.TRUE.equals(canUpdate))
        {
          var baseInfo = baseInfoOption.get();
          historyUpdateAdvanceService.updateChangeLog(baseInfo, new JSONObject()
            .put("entity", new Gson().toJson(advanceMapperBean.cleanEntity(entityOld.get())))
            .put("baseInfo", new Gson().toJson(baseInfoAdvanceService.publicLocal().advanceMapper.cleanEntity(baseInfo)))
            .toString());
          baseInfo = baseInfoAdvanceService.publicLocal().advanceRepository.save(baseInfo
            .modifiedDate(Instant.now())
//            .processStatus(ProcessStatus.PROCESSING)
            .modifiedBy(MasterUserUtil.getCurrentMasterUser().get()));
          if (ReflectUtil.methodInvoke(entity, "getInfo").isEmpty())
            ReflectUtil.methodInvoke(entity, "setInfo", baseInfo).get();
        }
        else
        {
          log.warn("not found base info or no access");
          return null;
        }
      }
      var result = joinPoint.proceed(new Object[]{entity});
      log.debug("entity after save entity : {}", entity);
      return result;
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }

}
