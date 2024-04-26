package com.kopanusa.core.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author mrizkir
 */
@Service
class JwtService 
{
  public static String SECRET_KEY = "1234";
  
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails)
  {
    return "";
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
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
  }
}
