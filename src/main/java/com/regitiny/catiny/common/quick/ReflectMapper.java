package com.regitiny.catiny.common.quick;

import com.regitiny.catiny.common.utils.StringPool;
import io.vavr.control.Try;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.regitiny.catiny.common.utils.ReflectUtils.methodInvoke;

@Log4j2
public class ReflectMapper<R, T>
{
  @Getter
  private final R resource;
  @Getter
  private final T target;

  public ReflectMapper(R resource, T target)
  {
    this.resource = resource;
    this.target = target;
  }

  public static ReflectMapper<Object, Object> of(Object resource, Object target)
  {
    return new ReflectMapper<>(resource, target);
  }

  private String get(String fieldName)
  {
    return "get" + StringUtils.capitalize(fieldName);
  }

  private String set(String fieldName)
  {
    return "set" + StringUtils.capitalize(fieldName);
  }

  public ReflectMapper<R, T> add(String methodName)
  {
    var fieldName = StringUtils
      .capitalize((methodName.matches("get[A-Z0-9][\\w]*") || methodName.matches("set[A-Z0-9][\\w]*")) ?
        methodName.substring(3) :
        methodName);
    methodInvoke(resource, get(fieldName))
      .forEach(rgR -> methodInvoke(target, set(fieldName), rgR));
    return this;
  }

  /**
   * use simple regex "info1.{a,b,c,d};info2.{a2,b2,d2}" or "info1.[a,b,c,d];info2.[a2,b2,d2]" or "a;b;c;d". only processing one level
   * <p></p>
   * <p>Note: phiên bản hiện tại chỉ hỗ trợ những Type cơ bản của java </p>
   *
   * @param regex
   * @return this
   */
  public ReflectMapper<R, T> addByRegex(String regex)
  {
    if (regex.matches("[[\\w]+\\.[\\[{][\\w]+[,[\\w]+]*[}\\][;]*]]*"))
      Arrays.stream(regex.split(StringPool.SEMICOLON)).parallel()
        .forEach(eo ->
        {
          var element = eo.split(StringPool.DOT_IN_REGEX);
          var fieldName = element[0];
          if (element.length == 1 && eo.matches("[\\w]+"))
            this.add(eo);
          else if (element.length == 2 && element[1].matches("\\{[[,]*[\\w]+]*}"))
          {
            methodInvoke(resource, get(fieldName))
              .forEach(rgR ->
                methodInvoke(target, get(fieldName))
                  .orElse(() -> Try.of(() -> target.getClass()
                      .getMethod(get(fieldName))
                      .getReturnType()
                      .getName())
                    .mapTry(Class::forName)
                    .mapTry(aClass -> aClass.getConstructor().newInstance()).toOption())
                  .forEach(rgT ->
                  {
                    var elementMapper = of(rgR, rgT);
                    List.of(element[1]
                        .substring(1, element[1].length() - 1)
                        .split(StringPool.COMMA))
                      .parallelStream()
                      .forEach(elementMapper::add);
                    methodInvoke(target, set(fieldName), rgT);
                  }));
          }
          else if (element.length == 2 && element[1].matches("\\[[\\w]+[,[\\w]+]*]"))
          {
            var fieldNames = fieldName + "s";
            methodInvoke(resource, get(fieldNames))
              .forEach(rgR ->
              {
                var resultTarget = new HashSet<>();
                ((Set<?>) rgR).forEach(rgRElement ->
                  Try.of(() -> target.getClass()
                      .getMethod(get(fieldNames))
                      .getGenericReturnType()
                      .getTypeName())
                    .filter(s -> s.contains(Set.class.getName()))
                    .map(s -> s.substring(Set.class.getName().length() + 1, s.length() - 1))
                    .peek(log::debug)
                    .mapTry(Class::forName)
                    .mapTry(aClass -> aClass.getConstructor().newInstance()).toOption()
                    .forEach(rgT ->
                    {
                      var elementMapper = of(rgRElement, rgT);
                      List.of(element[1]
                          .substring(1, element[1].length() - 1)
                          .split(StringPool.COMMA))
                        .parallelStream().forEach(elementMapper::add);
                      resultTarget.add(rgT);
                    }));
                methodInvoke(target, set(fieldNames), new Class[]{Set.class}, resultTarget);
              });
          }
        });
    else
      log.warn("Syntax error: {} , \n-> example: field.{cf1,cf2,cf2};field2.{cf21,cf22,cf23}", regex);
    return this;
  }

  public ReflectMapper<R, T> add(Method method)
  {
    add(method.getName());
    return this;
  }

  public ReflectMapper<R, T> adds(List<String> names)
  {
    names.parallelStream().forEach(this::add);
    return this;
  }

}
