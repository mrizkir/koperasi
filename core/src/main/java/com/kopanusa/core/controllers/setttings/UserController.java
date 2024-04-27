package com.kopanusa.core.controllers.setttings;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kopanusa.core.services.ServiceResponse;

import com.kopanusa.core.services.settings.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/setting/users")
@RestController
@RequiredArgsConstructor
public class UserController 
{
  private final UserService service;
  @PostMapping("/store")
  public ResponseEntity<ServiceResponse> store(@RequestBody UserRequestBody request)
  {
    return ResponseEntity.ok(service.store(request));
  }
}
