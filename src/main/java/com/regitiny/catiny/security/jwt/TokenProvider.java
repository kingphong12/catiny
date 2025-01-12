package com.regitiny.catiny.security.jwt;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.util.MasterUserUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tech.jhipster.config.JHipsterProperties;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@GeneratedByJHipster
public class TokenProvider
{

  private static final String AUTHORITIES_KEY = "auth";
  private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
  private final Key key;

  private final JwtParser jwtParser;

  private final long tokenValidityInMilliseconds;

  private final long tokenValidityInMillisecondsForRememberMe;

  public TokenProvider(JHipsterProperties jHipsterProperties)
  {
    byte[] keyBytes;
    String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret();
    if (!ObjectUtils.isEmpty(secret))
    {
      log.debug("Using a Base64-encoded JWT secret key");
      keyBytes = Decoders.BASE64.decode(secret);
    }
    else
    {
      log.warn(
        "Warning: the JWT key used is not Base64-encoded. " +
          "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security."
      );
      secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
      keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    }
    key = Keys.hmacShaKeyFor(keyBytes);
    jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
    this.tokenValidityInMillisecondsForRememberMe =
      1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
  }

  public Claims getClaims(String jwt)
  {
    return jwtParser.parseClaimsJws(jwt).getBody();
  }

  public String createToken(Authentication authentication, boolean rememberMe)
  {
    String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    var masterUser = Try.of(() -> MasterUserUtils.getMasterUserDTOByLogin(authentication.getName()).get()).getOrElse(new MasterUserDTO());

    long now = (new Date()).getTime();
    Date validity;
    if (rememberMe)
    {
      validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
    }
    else
    {
      validity = new Date(now + this.tokenValidityInMilliseconds);
    }

    return Jwts
      .builder()
      .setSubject(authentication.getName())
      .claim("masterUserId", masterUser.getUuid())
      .claim("fullName", masterUser.getFullName())
      .claim("sessionKey", UUID.randomUUID().toString())
      .claim(AUTHORITIES_KEY, authorities)
      .signWith(key, SignatureAlgorithm.HS512)
      .setExpiration(validity)
      .compact();
  }

  public Authentication getAuthentication(String token)
  {
    Claims claims = jwtParser.parseClaimsJws(token).getBody();

    Collection<? extends GrantedAuthority> authorities = Arrays
      .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
      .filter(auth -> !auth.trim().isEmpty())
      .map(SimpleGrantedAuthority::new)
      .collect(Collectors.toList());

    User principal = new User(claims.getSubject(), "", authorities);

    return new UsernamePasswordAuthenticationToken(principal, token, authorities);
  }

  public boolean validateToken(String authToken)
  {
    try
    {
      jwtParser.parseClaimsJws(authToken);
      return true;
    }
    catch (JwtException | IllegalArgumentException e)
    {
      log.info("Invalid JWT token.");
      log.trace("Invalid JWT token trace.", e);
    }
    return false;
  }
}
