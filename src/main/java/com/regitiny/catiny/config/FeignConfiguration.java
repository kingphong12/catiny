package com.regitiny.catiny.config;

import feign.Logger.Level;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = "com.regitiny.catiny")
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration
{
  /**
   * Set the Feign specific log level to log client REST requests.
   */
  @Bean
  Level feignLoggerLevel()
  {
    return Level.BASIC;
  }

//  /**
//   * activated @RequestLine annotation of feign
//   */
//  @Bean
//  public Contract useFeignAnnotations() {
//    return new Contract.Default();
//  }
}
