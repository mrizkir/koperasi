package com.kopanusa.core.services;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
  @JsonProperty("error_code")
  private int errorCode;

  @JsonProperty("error_desc")
  private String errorDesc;

  // @JsonProperty("payload")
  // private String payload;
  @JsonProperty("payload")
  private Map<String, Object> payload = new HashMap<String, Object>();
}
