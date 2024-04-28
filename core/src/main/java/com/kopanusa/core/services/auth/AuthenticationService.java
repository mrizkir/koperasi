package com.kopanusa.core.services.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.kopanusa.core.controllers.auth.LoginRequestBody;
import com.kopanusa.core.models.auth.UserModel;
import com.kopanusa.core.models.auth.TokenModel;
import com.kopanusa.core.models.auth.TokenTypeEnum;
import com.kopanusa.core.services.ServiceResponse;
import com.kopanusa.core.repositories.auth.TokenRepository;
import com.kopanusa.core.repositories.auth.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService 
{
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final TokenRepository tokenRepository;

  public ServiceResponse authenticate(LoginRequestBody request)
  {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    
    var token = jwtService.generateToken(request.getUsername());

    Map<String, Object> data = new HashMap<>();
    data.put("token", token);
    
    var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
    revokeAllUserTokens(user);
    saveUserToken(user, token);

    return ServiceResponse
      .builder()
      .errorCode(0)
      .errorDesc("")
      .payload(data)
      .build();
  }  

  public ServiceResponse me()
  {
    var user = userRepository.findByUsername("admin");
    
    Map<String, Object> data = new HashMap<>();
    data.put("user", user);

    return ServiceResponse
      .builder()
      .errorCode(0)
      .errorDesc("")
      .payload(data)
      .build();
  }

  private void revokeAllUserTokens(UserModel user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());

    if (validUserTokens.isEmpty())
      return;

    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);      
    });

    tokenRepository.saveAll(validUserTokens);
  }

  private void saveUserToken(UserModel user, String token) 
  {
    TokenModel jwtToken = TokenModel.builder()
        .user(user)
        .token(token)
        .tokenType(TokenTypeEnum.BEARER)
        .expired(false)
        .revoked(false)
        .build();

    tokenRepository.save(jwtToken);
  }
}
