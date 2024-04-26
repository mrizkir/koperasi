package com.kopanusa.core.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author mrizkir
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
  private final JwtService jwtService;
  
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException 
  {
    final String authHeader = request.getHeader("Authorization");
    final String token;    
    final String username;    
    
    if(authHeader == null || !authHeader.startsWith("Bearer "))
    {
      filterChain.doFilter(request, response);
      return;
    }
    else
    {
      token = authHeader.substring(7);
      username = jwtService.extractUsername(token);
    }
  }
  
}
