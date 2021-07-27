package com.regitiny.catiny.util;

import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
public class ReflectUtil
{
  private final Object classInvoke;

  public static Option<Object> methodInvoke(Object object, String methodName, Object... parameter)
  {
    return methodInvoke(object,
      methodName,
      Arrays.stream(parameter).map(Object::getClass).toArray(Class[]::new),
      parameter);
  }

  public static Option<Object> methodInvoke(Object object, String methodName, Class<?>[] parameterTypes, Object... parameter)
  {
    return Try.of(() -> object.getClass()
      .getMethod(methodName, parameterTypes)
      .invoke(object, parameter))
      .onFailure(throwable -> log.error("error when invoke method -> method name : {} \nparameter : {}", methodName, parameter, throwable))
      .map(o ->
      {
        if (o.getClass().equals(Optional.class))
          return ((Optional) o).get();
        else if (Objects.equals(o.getClass(), Option.class))
          return ((Option) o).get();
        return o;
      })
      .toOption();
  }

  public Option<Object> methodInvoke(String methodName, Object... parameter)
  {
    return methodInvoke(classInvoke, methodName, parameter);
  }

  public Option<Object> methodInvoke(String methodName, Class<?>[] parameterTypes, Object... parameter)
  {
    return methodInvoke(classInvoke, methodName, parameterTypes, parameter);
  }

}
