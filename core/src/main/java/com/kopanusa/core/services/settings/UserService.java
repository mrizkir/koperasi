package com.kopanusa.core.services.settings;


import org.springframework.stereotype.Service;

import com.kopanusa.core.controllers.setttings.UserRequestBody;
import com.kopanusa.core.helpers.HelperUser;
import com.kopanusa.core.models.auth.UserModel;
import com.kopanusa.core.repositories.auth.UserRepository;
import com.kopanusa.core.services.ServiceResponse;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService 
{
  private final UserRepository repository;

  public ServiceResponse store(UserRequestBody request)
  {
    var user = UserModel.builder()
    .username(request.getUsername())
    .password(HelperUser.passwordEncoder().encode(request.getPassword()))
    .nama_lengkap(request.getNamaLengkap())
    .email(request.getEmail())
    .nomor_hp(request.getNomorHP())
    .role(request.getRole())
    .build();

    repository.save(user);

    Map<String, Object> data = new HashMap<>();
    data.put("user", user);
    
    return ServiceResponse
      .builder()
      .errorCode("0")
      .errorDesc("")
      .payload(data)
      .build();
  }
}