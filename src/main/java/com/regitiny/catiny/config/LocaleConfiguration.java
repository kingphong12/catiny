package com.regitiny.catiny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import tech.jhipster.config.locale.AngularCookieLocaleResolver;

import java.util.List;

@Configuration
public class LocaleConfiguration implements WebMvcConfigurer
{

  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    var localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("language");
    registry.addInterceptor(deviceResolverHandlerInterceptor());
    registry.addInterceptor(localeChangeInterceptor);
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
  {
    argumentResolvers.add(deviceHandlerMethodArgumentResolver());
  }

  @Bean
  public LocaleResolver localeResolver()
  {
    var cookieLocaleResolver = new AngularCookieLocaleResolver();
    cookieLocaleResolver.setCookieName("NG_TRANSLATE_LANG_KEY");
    return cookieLocaleResolver;
  }

  @Bean
  public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor()
  {
    return new DeviceResolverHandlerInterceptor();
  }


  @Bean
  public DeviceHandlerMethodArgumentResolver
  deviceHandlerMethodArgumentResolver()
  {
    return new DeviceHandlerMethodArgumentResolver();
  }
}
