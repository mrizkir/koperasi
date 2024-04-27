package com.kopanusa.core.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.util.Map;
import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * diperoleh dari https://github.com/ali-bouali/spring-boot-3-jwt-security/
 * @author mrizkir
 */
@Service
public class JwtService 
{ 
  private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration)
  {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
  {
    return Jwts
    .builder()
    .setClaims(extraClaims)
    .setSubject(userDetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
    .compact();
  }

  public String generateToken(UserDetails userDetails)
  {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, CoreConfig.getJwtExpiration());
  }

  public boolean isTokenValid(String token, UserDetails userDetails)
  {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public String extractUsername(String token)
  {
    return extractClaim(token, Claims::getSubject);
  }
  
  private Claims extractAllClaim(String token)
  {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();            
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimResolver)
  {
    final Claims claims = extractAllClaim(token);
    return claimResolver.apply(claims);
  }

  private Key getSignInKey()
  {
    String secretKey = CoreConfig.getJwtSecretKey();
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
