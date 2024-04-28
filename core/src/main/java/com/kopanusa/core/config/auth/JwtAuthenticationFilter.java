package com.kopanusa.core.config.auth;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.kopanusa.core.repositories.auth.TokenRepository;
import com.kopanusa.core.services.auth.JwtService;

import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException 
  {
    final String authHeader = request.getHeader("Authorization");
    final String token;
    final String userName;

    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    token = authHeader.substring(7);
    userName = jwtService.extractUsername(token);

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null)
    {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
      var isTokenValid = tokenRepository.findByToken(token)
        .map(t -> !t.isExpired() && !t.isRevoked())
        .orElse(false);

      if(jwtService.isTokenValid(token, userDetails) && isTokenValid)
      {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    
    filterChain.doFilter(request, response);
  }
  
}