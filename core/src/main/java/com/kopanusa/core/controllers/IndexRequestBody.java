package com.kopanusa.core.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexRequestBody 
{
  private String filter;
  private String order;
  private String limit;
  private String offset;
}
