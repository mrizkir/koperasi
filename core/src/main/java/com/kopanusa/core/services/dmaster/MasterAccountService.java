package com.kopanusa.core.services.dmaster;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kopanusa.core.controllers.IndexRequestBody;
import com.kopanusa.core.controllers.dmaster.MasterAccountRequestBody;
import com.kopanusa.core.models.master.MasterAccountModel;
import com.kopanusa.core.repositories.master.MasterAccountRepository;
import com.kopanusa.core.services.ServiceResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasterAccountService 
{

  private final MasterAccountRepository repository;

  public ServiceResponse index(IndexRequestBody request) 
  {
    var accounts = repository.findAll();

    Map<String, Object> data = new HashMap<>();
    data.put("list", accounts);

    return ServiceResponse
      .builder()
      .errorCode("0")
      .errorDesc("")
      .payload(data)
      .build();
  }

  public ServiceResponse store(MasterAccountRequestBody request)
  {
    var account = MasterAccountModel.builder()
    .id(request.getId())
    .name(request.getName())
    .normal(request.getNormal())
    .build();

    repository.save(account);
    
    Map<String, Object> data = new HashMap<>();
    data.put("account", account);

    return ServiceResponse
    .builder()
    .errorCode("0")
    .errorDesc("")
    .payload(data)
    .build();
  }
}
