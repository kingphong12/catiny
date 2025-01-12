package com.regitiny.catiny.config;

import com.regitiny.catiny.security.AuthoritiesConstants;
import com.regitiny.catiny.security.jwt.JWTConfigurer;
import com.regitiny.catiny.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final JHipsterProperties jHipsterProperties;

  private final TokenProvider tokenProvider;

  private final CorsFilter corsFilter;
  private final SecurityProblemSupport problemSupport;

  public SecurityConfiguration(
    TokenProvider tokenProvider,
    CorsFilter corsFilter,
    JHipsterProperties jHipsterProperties,
    SecurityProblemSupport problemSupport
  ) {
    this.tokenProvider = tokenProvider;
    this.corsFilter = corsFilter;
    this.problemSupport = problemSupport;
    this.jHipsterProperties = jHipsterProperties;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) {
    web
      .ignoring()
      .antMatchers(HttpMethod.OPTIONS, "/**")
      .antMatchers("/app/**/*.{js,html}")
      .antMatchers("/i18n/**")
      .antMatchers("/content/**")
      .antMatchers("/h2-console/**")
      .antMatchers("/swagger-ui/**")
      .antMatchers("/test/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .csrf()
      .disable()
      .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling()
      .authenticationEntryPoint(problemSupport)
      .accessDeniedHandler(problemSupport)
      .and()
      .headers()
//      .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())
      .contentSecurityPolicy("default-src 'self'; " +
        "connect-src 'self'  wss://0.peerjs.com https://0.peerjs.com; " +
        "frame-src 'self' https://www.google.com/maps/embed https://www.youtube.com data:; " +
        "script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; " +
        "style-src 'self' https://fonts.googleapis.com 'unsafe-inline'; " +
        "img-src * 'self' data:; " +
        "media-src 'self' data: blob: ; " +
        "font-src 'self' https://fonts.gstatic.com data:")
      .and()
      .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
      .and()
//      .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; fullscreen 'self'; payment 'none'")
      .featurePolicy("geolocation 'none'; " +
        "midi 'none'; " +
        "sync-xhr 'none'; " +
        "microphone 'self'; " +
        "camera 'self'; " +
        "magnetometer 'none'; " +
        "gyroscope 'none'; " +
        "fullscreen 'self'; " +
        "payment 'none'")
      .and()
      .frameOptions()
      .deny()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers("/api/authenticate").permitAll()
      .antMatchers("/api/register").permitAll()
      .antMatchers("/api/activate").permitAll()
      .antMatchers("/api/account/reset-password/init").permitAll()
      .antMatchers("/api/account/reset-password/finish").permitAll()
      .antMatchers("/api/account").authenticated()
      .antMatchers("/api/open/**").permitAll()
//    /api/admin/**/_management
//    /api/admin/**
      .antMatchers(HttpMethod.GET, "/api/admin/**/_management").hasAnyAuthority(AuthoritiesConstants.MANAGEMENT, AuthoritiesConstants.ADMIN)
      .antMatchers(HttpMethod.GET, "/api/admin/**").hasAnyAuthority(AuthoritiesConstants.MANAGEMENT, AuthoritiesConstants.ADMIN)
      .antMatchers("/api/admin/**/_management").hasAuthority(AuthoritiesConstants.ADMIN)
      .antMatchers("/api/admin/**").hasAnyAuthority(AuthoritiesConstants.ADMIN)
//    /api/o/**
      .antMatchers(HttpMethod.GET, "/api/o/images/**").permitAll()
      .antMatchers(HttpMethod.GET, "/api/o/videos/**").permitAll()
      .antMatchers("/api/o/images/**").authenticated()
      .antMatchers("/api/o/videos/**").authenticated()
      .antMatchers("/api/o/**").authenticated()
//    /api/**
      .antMatchers("/api/**").hasAnyAuthority(AuthoritiesConstants.MANAGEMENT, AuthoritiesConstants.ADMIN)
      .antMatchers("/websocket/**").authenticated()
      .antMatchers("/management/health").permitAll()
      .antMatchers("/management/health/**").permitAll()
      .antMatchers("/management/info").permitAll()
      .antMatchers("/management/prometheus").permitAll()
      .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
      .and()
      .httpBasic()
      .and()
      .apply(securityConfigurerAdapter());
    // @formatter:on
  }

  private JWTConfigurer securityConfigurerAdapter() {
    return new JWTConfigurer(tokenProvider);
  }
}
