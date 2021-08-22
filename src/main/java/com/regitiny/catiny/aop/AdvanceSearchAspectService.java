package com.regitiny.catiny.aop;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Aspect for AdvanceSearch.
 */
@Aspect
@Component
@Order(2)
@RequiredArgsConstructor
@Transactional
public class AdvanceSearchAspectService
{
  private final Environment env;
  private final ApplicationContext applicationContext;

  /**
   * Pointcut that matches all method save in advance.repository and advance.repository.base.
   */
  @Pointcut(
    " (execution(* com.regitiny.catiny.advance.repository.*.save(..)) || " +
//      " execution(* com.regitiny.catiny.repository.*.save(..))) ||" +
      " execution(* com.regitiny.catiny.advance.repository.base.*.save(..))) " +
      " && !execution(* com.regitiny.catiny.repository.UserRepository.save(..))"
  )
  public void repositorySavePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(
    " (execution(* com.regitiny.catiny.advance.repository.*.delete(..)) || " +
//      " execution(* com.regitiny.catiny.repository.*.delete(..))) ||" +
      " execution(* com.regitiny.catiny.advance.repository.base.*.delete(..))) " +
      " && !execution(* com.regitiny.catiny.repository.UserRepository.delete(..))"
  )
  public void repositoryDeletePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * xử lý lưu vào elasticsearch khi thực hiện lưu vào db xong
   *
   * @param joinPoint join point for advice.
   * @return result.
   * @throws Throwable throws {@link IllegalArgumentException}.
   */
  @Around("repositorySavePointcut() ")
  public Object aroundRepositorySave(ProceedingJoinPoint joinPoint) throws Throwable
  {
    Logger log = logger(joinPoint);

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    try
    {
      var args = joinPoint.getArgs();
      if (args.length != 1)
        return joinPoint.proceed();
      var entity = args[0];
      var repoResult = joinPoint.proceed();
      var entityName = entity.getClass().getSimpleName();
      var mapper = applicationContext.getBean(StringUtils.uncapitalize(entityName) + "MapperImpl");// entityNameMapperImpl
      var entityDTO = mapper.getClass().getMethod("toDto", Class.forName("com.regitiny.catiny.domain." + entityName)).invoke(mapper, repoResult);
      var entityResult = mapper.getClass().getMethod("toEntity", Class.forName("com.regitiny.catiny.service.dto." + entityName + "DTO")).invoke(mapper, entityDTO);

      log.debug("original data : {} = ", entity);
      log.debug("processed data : {} = ", entityResult);
      applicationContext.getBean(this.getClass()).saveToElasticsearch(entityName, repoResult, log);
      return repoResult;
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }

  /**
   * xử lý xóa của es khi db xóa
   *
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("repositoryDeletePointcut() ")
  public Object aroundRepositoryDelete(ProceedingJoinPoint joinPoint) throws Throwable
  {
    Logger log = logger(joinPoint);

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    try
    {
      var args = joinPoint.getArgs();
      if (args.length != 1)
        return joinPoint.proceed();
      var entity = args[0];
      var entityName = entity.getClass().getSimpleName();
      var result = joinPoint.proceed();
      applicationContext.getBean(this.getClass()).deleteInElasticsearch(entityName, result, log);
      return result;
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
  }

  @Async
  public void saveToElasticsearch(String entityName, Object data, Logger log) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    var advanceSearch = applicationContext.getBean(StringUtils.uncapitalize(entityName) + "AdvanceSearch");// entityNameMapperImpl
    var resultSearch = advanceSearch.getClass().getMethod("save", Object.class).invoke(advanceSearch, data);
    log.debug("after save to elasticsearch : {}", resultSearch);
  }

  @Async
  public void deleteInElasticsearch(String entityName, Object data, Logger log) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    var advanceSearch = applicationContext.getBean(StringUtils.uncapitalize(entityName) + "AdvanceSearch");// entityNameMapperImpl
    var resultSearch = advanceSearch.getClass().getMethod("delete", Object.class).invoke(advanceSearch, data);
    log.debug("after delete in elasticsearch : {}", resultSearch);
  }


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

  private Logger thisLog()
  {
    return LoggerFactory.getLogger(AdvanceSearchAspectService.class);
  }

}
