package com.kopanusa.core.controllers.dmaster;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kopanusa.core.controllers.IndexRequestBody;
import com.kopanusa.core.services.ServiceResponse;
import com.kopanusa.core.services.dmaster.MasterAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/dmaster/accounts")
@RequiredArgsConstructor
public class MasterAccountController
{
  private final MasterAccountService service;
  public ResponseEntity<ServiceResponse> index(@RequestBody IndexRequestBody request)
  {
    return ResponseEntity.ok(service.index(request));
  }
}
