package com.regitiny.catiny.aop;

import com.regitiny.catiny.service.dto.UserDTO;
import io.vavr.Function2;
import io.vavr.collection.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.regitiny.catiny.common.utils.ReflectUtils.methodInvoke;


/**
 * Aspect for AdvanceRepository.
 */

@Aspect
@Log4j2
@Component
@Transactional
@RequiredArgsConstructor
public class AdvanceMapperAspectService
{
  private final Environment env;

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
   * khi chuyển từ entity sang dto xóa hết id và thêm uuid vào
   *
   * @param joinPoint join point
   * @return result of joinPoint.proceed()
   * @throws Throwable err
   */
  @Around(" execution(* com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper.e2d(..)) ")
  public Object aroundCleanIdAddUuid(ProceedingJoinPoint joinPoint) throws Throwable
  {
    var logJP = logger(joinPoint);
    var permissionNeedCheck = env.getProperty("catiny.dto.only-uuid", Boolean.class, true);
    if (Boolean.FALSE.equals(permissionNeedCheck))
      return joinPoint.proceed();
    if (joinPoint.getArgs().length != 1)
      return joinPoint.proceed();
    var entityOriginal = joinPoint.getArgs()[0];
    //Todo : là list


    logJP.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());

    var resultDTO = joinPoint.proceed();

    Function2<Object, Object, ?> cleanIdAddUuid = (dto, ent) -> methodInvoke(dto, "getId")
      .map(Long.class::cast)
      .map(id ->
      {
        methodInvoke(dto, "setId", new Class[]{Long.class}, (Object) null);
        methodInvoke(ent, "getUuid").forEach(entityUuid -> methodInvoke(dto, "setUuid", entityUuid));
        return null;
      }).get();

    cleanIdAddUuid.apply(resultDTO, entityOriginal);

    var resultDTOMethod = List.of(resultDTO.getClass().getMethods());

    resultDTOMethod.filter(method -> method.getName().matches("get[A-Z][\\w]+"))
      .filter(method ->
      {
        var typeName = method.getGenericReturnType().getTypeName();
        return typeName.matches("com.regitiny.catiny.service.dto.[A-Z]+[A-z0-9]+DTO") &&
          !UserDTO.class.equals(method.getGenericReturnType());
      })
      .forEach(method ->
      {
        var objectInEntity = methodInvoke(entityOriginal, method.getName()).getOrNull();
        var objectInDTO = methodInvoke(resultDTO, method.getName()).getOrNull();
        if (Objects.nonNull(objectInDTO) && Objects.nonNull(objectInEntity))
          cleanIdAddUuid.apply(objectInDTO, objectInEntity);
      });

    resultDTOMethod.filter(method -> method.getName().matches("get[A-Z][\\w]+"))
      .filter(method -> method.getGenericReturnType().getTypeName().matches("java.util.Set<com.regitiny.catiny.service.dto.[A-Z]+[A-z0-9]+DTO>"))
      .forEach(method ->
      {
        var methodName = method.getName();
        var entitySet = methodInvoke(entityOriginal, methodName).map(o -> (Set<?>) o).getOrElse(new HashSet<>());
        var dtoSet = methodInvoke(resultDTO, methodName).map(o -> (Set<?>) o).getOrElse(new HashSet<>());
        var entityUuidMap = new HashMap<Long, UUID>();
        var dtoMap = new HashMap<Long, Object>();
        entitySet.forEach(e -> entityUuidMap.put(methodInvoke(e, "getId").map(Long.class::cast).get(), methodInvoke(e, "getUuid").map(UUID.class::cast).get()));
        dtoSet.forEach(d -> dtoMap.put(methodInvoke(d, "getId").map(Long.class::cast).get(), d));
        dtoMap.forEach((id, dto) -> methodInvoke(dto, "setUuid", entityUuidMap.get(id)));
      });


    return resultDTO;
  }


}
