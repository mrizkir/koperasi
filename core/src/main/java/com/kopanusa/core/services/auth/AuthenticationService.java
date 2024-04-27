package com.kopanusa.core.services.auth;

import java.util.HashMap;
import java.util.Map;

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
  
  public ServiceResponse authenticate(LoginRequestBody request)
  {
    var user = userRepository.findByUsername(request.getUsername());
    
    var token = "123";
    // var token = jwtService.generateToken(user);
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
