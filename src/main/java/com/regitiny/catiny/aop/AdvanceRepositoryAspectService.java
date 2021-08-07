package com.regitiny.catiny.aop;

import com.regitiny.catiny.advance.service.impl.BaseInfoAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.HistoryUpdateAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.MasterUserAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.PermissionAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.util.MasterUserUtil;
import com.regitiny.catiny.util.ReflectUtil;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
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
   * @param joinPoint
   * @return
   * @throws Throwable
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
    Logger log = logger(joinPoint);
    var permissionNeedCheck = env.getProperty("catiny.permission.need-check", Boolean.class, true);
    if (!permissionNeedCheck)
      return joinPoint.proceed();

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
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

      var advanceRepositoryBean = joinPoint.getTarget();// applicationContext.getBean(advanceRepositoryName.get(0));
      var advanceMapperBean = (EntityAdvanceMapper) applicationContext.getBean(advanceRepositoryName.get(0).replace("AdvanceRepository", "AdvanceMapperImpl"));
      var advanceRepositoryBeanInvoker = new ReflectUtil(advanceRepositoryBean);

      ReflectUtil.methodInvoke(entity, "getId")
        .map(id -> advanceRepositoryBeanInvoker.methodInvoke("findById", new Class[]{Object.class}, id).get())
        .orElse(ReflectUtil.methodInvoke(entity, "getUuid")
          .map(uuid -> advanceRepositoryBeanInvoker.methodInvoke("findOneByUuid", uuid).getOrNull()))
        .peek(entityExist -> log.debug("entity really exist in database -> update entity . entity details : {}", entityExist))
//        update
        .map(entityExist -> ReflectUtil.methodInvoke(entityExist, "getInfo")
          .map(o -> (BaseInfo) o)
          //kiểm tra quyền ghi
          .filter(baseInfo -> Option.of(baseInfo.getOwner())
            .map(MasterUser::getId)
            .map(id -> id.equals(MasterUserUtil.getCurrentMasterUser().map(MasterUser::getId).get()))
            .orElse(permissionAdvanceService.publicLocal().advanceRepository
              .findOneByBaseInfoAndOwner(baseInfo, MasterUserUtil.getCurrentMasterUser().getOrElse(MasterUserUtil.anonymousMasterUser().get()))
              .map(Permission::getWrite))
            .getOrElse(false))
          .peek(baseInfo -> historyUpdateAdvanceService.updateChangeLog(baseInfo, new JSONObject()
            .put("details-prev", advanceMapperBean.cleanEntity(entityExist))
            .put("base-info-prev", baseInfoAdvanceService.publicLocal().advanceMapper.cleanEntity(baseInfo))
            .toString()))
          .map(baseInfo -> baseInfoAdvanceService.publicLocal().advanceRepository.save(baseInfo
            .modifiedDate(Instant.now())
//            .processStatus(ProcessStatus.PROCESSING)
            .modifiedBy(MasterUserUtil.getCurrentMasterUser().get())))
          .map(baseInfo ->
          {
            if (ReflectUtil.methodInvoke(entity, "getInfo").isEmpty())
              return ReflectUtil.methodInvoke(entity, "setInfo", baseInfo).get();
            return entity;
          })
          .getOrElse(() ->
          {
            log.debug("không tìm thấy base info hoặc không có quyền truy cập ");
            return null;
          }))
//        create new entity
//        bỏ quả nếu nó là tạo mới MasterUser
        .orElse(Option.when(!advanceRepositoryName.get(0).toLowerCase().contains("MasterUser".toLowerCase()),
          ReflectUtil.methodInvoke(entity, "setInfo", baseInfoAdvanceService.createForOwner())))
        .getOrElse(entity);
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
