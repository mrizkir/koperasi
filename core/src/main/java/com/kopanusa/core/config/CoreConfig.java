package com.kopanusa.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class CoreConfig 
{
  @Value("${jwt.secret-key}")
  private static String JWT_SECRET_KEY;
  
  @Value("${jwt.expiration}")
  private static long JWT_EXPIRATION;
  
  @Value("${jwt.refresh-token.expiration}")
  private static long JWT_REFRESH_TOKEN_EXPIRATION;

  public static String getJwtSecretKey() {
    return JWT_SECRET_KEY;
  }

  public static long getJwtExpiration() {
    return JWT_EXPIRATION;
  }

  public static long getJwtRefreshToken() {
    return JWT_REFRESH_TOKEN_EXPIRATION;
  }
}
