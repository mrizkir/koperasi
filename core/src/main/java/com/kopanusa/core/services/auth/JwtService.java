package com.kopanusa.core.services.auth;

import java.security.Key;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import com.kopanusa.core.config.ApplicationConfig;

@Service
@RequiredArgsConstructor
public class JwtService
{
  private final ApplicationConfig appConfig;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
  {
    return buildToken(extraClaims, userDetails, appConfig.getExpiration());
  }

  public String generateToken(String username){
    Map<String, Object> claims = new HashMap<>();
    return buildToken(claims, username, appConfig.getExpiration());
  }

  public String generateRefreshToken(UserDetails userDetails) 
  {
    return buildToken(new HashMap<>(), userDetails, appConfig.getExpiration());
  }

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
  private String buildToken(Map<String, Object> extraClaims, String username, long expiration)
  {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(username)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + expiration))
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private Key getSignInKey() 
  {
    byte[] keyBytes = Decoders.BASE64.decode(appConfig.getSecretKey());
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
