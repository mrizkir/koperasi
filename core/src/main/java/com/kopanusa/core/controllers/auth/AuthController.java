package com.kopanusa.core.controllers.auth;

import org.springframework.web.bind.annotation.RestController;

import com.kopanusa.core.services.ServiceResponse;
import com.kopanusa.core.services.auth.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController
{
  private final AuthenticationService service;
  @PostMapping("/login")
  public ResponseEntity<ServiceResponse> authenticate(@Valid @RequestBody LoginRequestBody request)
  {
    return ResponseEntity.ok(service.authenticate(request));
  }
  @GetMapping("/me")
  public ResponseEntity<ServiceResponse> me()
  {
    return ResponseEntity.ok(service.me());
  }
}
