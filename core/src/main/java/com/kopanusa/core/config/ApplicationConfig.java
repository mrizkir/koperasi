package com.kopanusa.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig
{
  @Getter
  @Value("${jwt.secret-key}")
  private String secretKey;

  @Getter
  @Value("${jwt.expiration}")
  private long expiration;

  @Getter
  @Value("${jwt.refresh-token.expiration}")
  private long refreshTokenExpiration;
}
