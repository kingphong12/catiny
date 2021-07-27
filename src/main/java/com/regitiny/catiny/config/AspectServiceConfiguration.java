package com.regitiny.catiny.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.regitiny.catiny.aop")
@EnableAspectJAutoProxy
public class AspectServiceConfiguration
{
//  @Bean
//  @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
//  public AspectService aspectService(Environment env, ApplicationContext applicationContext)
//  {
//    return new AspectService(env, applicationContext);
//  }
//
//  @Bean
//  @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
//  public AdvanceRepositoryAspectService advanceRepositoryAspectService(Environment env, ApplicationContext applicationContext)
//  {
//    return new AdvanceRepositoryAspectService(env, applicationContext);
//  }
//
//  @Bean
//  @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
//  public AdvanceSearchAspectService advanceSearchAspectService(Environment env, ApplicationContext applicationContext)
//  {
//    return new AdvanceSearchAspectService(env, applicationContext);
//  }
}
