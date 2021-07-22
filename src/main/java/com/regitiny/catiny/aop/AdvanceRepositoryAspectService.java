package com.regitiny.catiny.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Aspect for AdvanceRepository.
 */
@Aspect
public class AdvanceRepositoryAspectService
{
  private final Environment env;
  private final ApplicationContext applicationContext;

  public AdvanceRepositoryAspectService(Environment env, ApplicationContext applicationContext)
  {
    this.env = env;
    this.applicationContext = applicationContext;
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Pointcut(
    " (execution(* com.regitiny.catiny.advance.repository.*.save(..)) ||" +
//      " execution(* com.regitiny.catiny.repository.*.save(..))) ||" +
      " execution(* com.regitiny.catiny.advance.repository.base*.save(..))) " +
      " && !execution(* com.regitiny.catiny.repository.UserRepository.save(..))"
  )
  public void repositorySavePointcut()
  {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * use to fix bug Jhipster
   * sau khi save thì dữ liệu hibernate lồng nhau bởi các relationship
   * với elasticsearch thì dữ liệu sẽ trở thành các jsonObject lồng nhau vô tận
   * nên tạm thời sử dụng method này dùng MapStruct để : Entity -> EntityDTO -> Entity
   *
   * @param joinPoint join point for advice.
   * @return result.
   * @throws Throwable throws {@link IllegalArgumentException}.
   */
  @Around("repositorySavePointcut() ")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable
  {
    Logger log = logger(joinPoint);

    log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    try
    {
      var args = joinPoint.getArgs();
      if (args.length != 1)
        return joinPoint.proceed();
      var entity = args[0];
      var methodGetUuid = entity.getClass().getMethod("getUuid");
      var uuid = methodGetUuid.invoke(entity);
      if (Objects.isNull(uuid))
        entity.getClass().getMethod("setUuid", UUID.class).invoke(entity, UUID.randomUUID());
      return joinPoint.proceed(new Object[]{entity});
    }
    catch (IllegalArgumentException e)
    {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
      throw e;
    }
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


}
