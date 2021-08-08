package com.regitiny.catiny.quick;

import com.regitiny.catiny.util.ReflectUtil;
import io.vavr.control.Option;

public interface ReflectQuick
{
  default Option<Object> methodInvoke(Object object, String methodName, Object... parameter)
  {
    return ReflectUtil.methodInvoke(object, methodName, parameter);
  }


  default Option<Object> methodInvoke(Object object, String methodName, Class<?>[] parameterTypes, Object... parameter)
  {
    return ReflectUtil.methodInvoke(object, methodName, parameterTypes, parameter);
  }
}
