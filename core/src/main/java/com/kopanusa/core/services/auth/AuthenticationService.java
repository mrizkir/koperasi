package com.kopanusa.core.services.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.kopanusa.core.controllers.auth.LoginRequestBody;
import com.kopanusa.core.services.ServiceResponse;
import com.kopanusa.core.repositories.auth.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService 
{
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ServiceResponse authenticate(LoginRequestBody request)
  {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    var user = userRepository.findByUsername(request.getUsername());
    
    // var token = "123";
    var token = jwtService.generateToken(request.getUsername());
    Map<String, Object> data = new HashMap<>();
    data.put("token", token);

    return ServiceResponse
      .builder()
      .errorCode(0)
      .errorDesc("")
      .payload(data)
      .build();
  }
  
  
}
