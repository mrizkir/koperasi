package com.kopanusa.core.services;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse 
{
  @NonNull
  @JsonProperty("error_code")
  private String errorCode;

  @NonNull
  @JsonProperty("error_desc")
  private String errorDesc;

  @NonNull
  @JsonProperty("payload")
  private Map<String, Object> payload = new HashMap<String, Object>();
}
