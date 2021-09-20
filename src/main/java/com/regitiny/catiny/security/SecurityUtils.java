package com.regitiny.catiny.security;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.security.jwt.TokenProvider;
import com.regitiny.catiny.util.ApplicationContextUtils;
import com.regitiny.catiny.web.rest.errors.NhechException;
import io.jsonwebtoken.Claims;
import io.vavr.control.Option;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
@GeneratedByJHipster
public final class SecurityUtils
{

  private SecurityUtils()
  {
  }

  /**
   * Get the login of the current user.
   *
   * @return the login of the current user.
   */
  public static Optional<String> getCurrentUserLogin() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
  }

  private static String extractPrincipal(Authentication authentication) {
    if (authentication == null) {
      return null;
    } else if (authentication.getPrincipal() instanceof UserDetails) {
      UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
      return springSecurityUser.getUsername();
    } else if (authentication.getPrincipal() instanceof String) {
      return (String) authentication.getPrincipal();
    }
    return null;
  }

  /**
   * Get the JWT of the current user.
   *
   * @return the JWT of the current user.
   */
  public static Optional<String> getCurrentUserJWT() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    return Optional
      .ofNullable(securityContext.getAuthentication())
      .filter(authentication -> authentication.getCredentials() instanceof String)
      .map(authentication -> (String) authentication.getCredentials());
  }

  /**
   * Check if a user is authenticated.
   *
   * @return true if the user is authenticated, false otherwise.
   */
  public static boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && getAuthorities(authentication).noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
  }

  /**
   * Checks if the current user has any of the authorities.
   *
   * @param authorities the authorities to check.
   * @return true if the current user has any of the authorities, false otherwise.
   */
  public static boolean hasCurrentUserAnyOfAuthorities(String... authorities) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (
      authentication != null && getAuthorities(authentication).anyMatch(authority -> Arrays.asList(authorities).contains(authority))
    );
  }

  /**
   * Checks if the current user has none of the authorities.
   *
   * @param authorities the authorities to check.
   * @return true if the current user has none of the authorities, false otherwise.
   */
  public static boolean hasCurrentUserNoneOfAuthorities(String... authorities) {
    return !hasCurrentUserAnyOfAuthorities(authorities);
  }

  /**
   * Checks if the current user has a specific authority.
   *
   * @param authority the authority to check.
   * @return true if the current user has the authority, false otherwise.
   */
  public static boolean hasCurrentUserThisAuthority(String authority) {
    return hasCurrentUserAnyOfAuthorities(authority);
  }

  private static Stream<String> getAuthorities(Authentication authentication)
  {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
  }

  public static void checkAuthenticationAndAuthority(String authority)
  {
    if (!isAuthenticated() || !hasCurrentUserThisAuthority(authority) || getCurrentUserLogin().isEmpty()) throw new NhechException(
      "kiểm tra lại xem đã đăng nhập hoặc đc cấp quyền chưa bạn êy"
    );
  }

  public static Option<String> getCurrentSessionKey()
  {
    return Option.of(ApplicationContextUtils.getApplicationContext().getBean(TokenProvider.class)
      .getClaims(getCurrentUserJWT().orElseGet(null))
      .get("sessionKey", String.class));
  }

  public static Option<Claims> getCurrentClaims()
  {
    return Option.of(ApplicationContextUtils.getApplicationContext().getBean(TokenProvider.class)
      .getClaims(getCurrentUserJWT()
        .orElseGet(null)));
  }


}
