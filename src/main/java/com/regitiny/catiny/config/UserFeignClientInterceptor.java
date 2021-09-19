package com.regitiny.catiny.config;

import com.regitiny.catiny.security.SecurityUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientInterceptor implements RequestInterceptor
{
  private static final String BEARER = "Bearer";

  @Override
  public void apply(RequestTemplate template)
  {
    SecurityUtils.getCurrentUserJWT().ifPresent(s -> template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", BEARER, s)));
  }
}
