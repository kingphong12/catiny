package com.regitiny.catiny.aop;

import com.google.gson.Gson;
import com.regitiny.catiny.advance.service.impl.BaseInfoAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.HistoryUpdateAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.MasterUserAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.PermissionAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.common.utils.ReflectUtil;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.regitiny.catiny.common.utils.ReflectUtil.methodInvoke;


/**
 * Aspect for AdvanceRepository.
 */

@Aspect
@Log4j2
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
    " execution(* com.regitiny.catiny..BaseInfo*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny..Permission*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny..HistoryUpdate*Repository.save(..)) ||" +
    " execution(* com.regitiny.catiny..ClassInfo*Repository.save(..)) " +
    ")")
  public Object aroundPermissionCheckWhenSave(ProceedingJoinPoint joinPoint) throws Throwable
  {
    var logJP = logger(joinPoint);
    var permissionNeedCheck = env.getProperty("catiny.permission.need-check", Boolean.class, true);
    if (Boolean.FALSE.equals(permissionNeedCheck))
      return joinPoint.proceed();

    logJP.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
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
      logJP.debug("entity original. entity : {}", entity);

      var advanceRepositoryBean = joinPoint.getTarget();
      @SuppressWarnings("unchecked")
      var advanceMapperBean = (EntityAdvanceMapper<?, ?, Object>) applicationContext.getBean(advanceRepositoryName.get(0).replace("AdvanceRepository", "AdvanceMapperImpl"));
      var advanceRepositoryBeanInvoker = new ReflectUtil(advanceRepositoryBean);

      var entityOld = methodInvoke(entity, "getId")
        .map(id -> advanceRepositoryBeanInvoker.methodInvoke("findById", new Class[]{Object.class}, id).getOrNull())
        .orElse(methodInvoke(entity, "getUuid")
          .map(uuid -> advanceRepositoryBeanInvoker.methodInvoke("findOneByUuid", uuid).getOrNull()));
      //kiểm tra quyền ghi

      if (entityOld.isDefined() || entityOld.isEmpty())
      {
        if (advanceRepositoryName.get(0).toLowerCase().contains("MasterUser".toLowerCase()))
          return joinPoint.proceed();
        methodInvoke(entity, "setInfo", baseInfoAdvanceService.createForOwner());
      }
      else
      {
        var baseInfoOption = methodInvoke(entityOld.get(), "getInfo")
          .map(BaseInfo.class::cast);
        var canUpdate = baseInfoOption
          .map(baseInfo -> Option.of(baseInfo.getOwner())
            .map(MasterUser::getId)
            .map(id -> id.equals(MasterUserUtil.getCurrentMasterUser().map(MasterUser::getId).get()))
            .orElse(permissionAdvanceService.publicLocal().advanceRepository
              .findOneByBaseInfoAndOwner(baseInfo, MasterUserUtil.getCurrentMasterUser().getOrElse(MasterUserUtil.getAnonymousMasterUser().get()))
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
          if (methodInvoke(entity, "getInfo").isEmpty())
            methodInvoke(entity, "setInfo", baseInfo).get();
        }
        else
        {
          logJP.warn("not found base info or no access");
          return null;
        }
      }
      var result = joinPoint.proceed(new Object[]{entity});
      logJP.debug("entity after save entity : {}", entity);
      return result;
    }
    catch (IllegalArgumentException e)
    {
      logJP.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }


  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(" within(com.regitiny.catiny.advance.repository.base..*+) ")
  public void baseRepositoryPointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(" execution(* com.regitiny.catiny.repository..*(..)) ")
  public void repositoryPointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(" " +
    " execution(* com.regitiny.catiny..BaseInfo*Repository.*(..)) ||" +
    " execution(* com.regitiny.catiny..Permission*Repository.*(..)) ||" +
    " execution(* com.regitiny.catiny..HistoryUpdate*Repository.*(..)) ||" +
    " execution(* com.regitiny.catiny..HistoryUpdate*Repository.*(..)) ||" +
    " execution(* com.regitiny.catiny..MasterUser*Repository.*(..)) ||" +
    " execution(* com.regitiny.catiny..ClassInfo*Repository.*(..)) " +
    " execution(* com.regitiny.catiny.advance.repository.base.BaseRepository.*(..)) " +
    " execution(* com.regitiny.catiny.advance.repository.CommonRepository.*(..)) "
  )
  public void excludePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }


  /**
   * quản lý tự động kiểm tra phân quyền thao tác với dữ liệu
   *
   * @param joinPoint join point
   * @return result of joinPoint.proceed
   * @throws Throwable err
   */
  @Around("baseRepositoryPointcut() && !(repositoryPointcut() || excludePointcut())")
  public Object aroundPermissionCheckWhenRead(ProceedingJoinPoint joinPoint) throws Throwable
  {
    var logJP = logger(joinPoint);
    logJP.debug("12345");
    Function1<java.util.List<?>, java.util.List<?>> checkPermissionList = (list) -> list.stream()
      .filter(o -> methodInvoke(o, "getInfo")
        .map(BaseInfo.class::cast)
        .filter(baseInfo -> !baseInfo.getDeleted())
        .flatMap(baseInfo -> permissionAdvanceService.publicLocal().advanceRepository
          .findOneByBaseInfoAndOwner(baseInfo, MasterUserUtil.getCurrentMasterUser()
            .getOrElse(MasterUserUtil.getAnonymousMasterUser().get())))
        .map(Permission::getRead)
        .getOrElse(false))
      .collect(Collectors.toList());
    var result = joinPoint.proceed();
    var resultTypeName = Objects.requireNonNull(result.getClass().getTypeName());
    if (resultTypeName.equals(PageImpl.class.getTypeName()))
    {
      var resultPage = (Page<?>) result;
      var resultAfterCheck = checkPermissionList.apply(resultPage.getContent());
      if (resultAfterCheck.size() == resultPage.getContent().size())
        return result;
      logJP.warn("exist problem with permission check . -> total-result = {} \n-> total-pass-check {}", resultPage.getContent().size(), resultAfterCheck.size());
//        new PageImpl<>(resultAfterCheck, ((Page<?>) result).getPageable(), resultPage.getSize());
      return Page.empty();
    }
    else if (resultTypeName.contains(List.class.getTypeName()))
    {
      var resultListVavr = (List<?>) result;
      var resultAfterCheck = checkPermissionList.apply(resultListVavr.toJavaList());
      if (resultAfterCheck.size() == resultListVavr.size())
        return result;
      logJP.warn("exist problem with permission check . -> total-result = {},total-pass-check {}", resultListVavr.size(), resultAfterCheck.size());
      return resultAfterCheck;
    }
    else if (resultTypeName.contains(Option.class.getTypeName()))
    {
      var resultOptionVavr = (Option<?>) result;
      var resultAfterCheck = checkPermissionList.apply(resultOptionVavr.toJavaList());
      if (resultAfterCheck.size() == 1)
        return result;
      logJP.warn("exist problem with permission check . result = {}", result);
      return Option.none();
    }
    else if (resultTypeName.equals(ArrayList.class.getTypeName()) || resultTypeName.equals(LinkedList.class.getTypeName()))
    {
      var resultListJava = (java.util.List<?>) result;
      var resultAfterCheck = checkPermissionList.apply(resultListJava);
      if (resultAfterCheck.size() == resultListJava.size())
        return result;
      logJP.warn("exist problem with permission check . -> total-result = {},total-pass-check {}", resultListJava.size(), resultAfterCheck.size());
      return resultAfterCheck;
    }
    else if (resultTypeName.equals(Optional.class.getTypeName()))
    {
      var resultListJava = (Optional<?>) result;
      var resultAfterCheck = checkPermissionList.apply(resultListJava.stream().collect(Collectors.toList()));
      if (resultAfterCheck.size() == 1)
        return result;
      logJP.warn("exist problem with permission check . result = {}", result);
      return Optional.empty();
    }
    else
    {
      logJP.warn("don't know type of result . -> default not check permission");
    }
    return result;
  }

}
