package com.kopanusa.core.controllers.dmaster;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterAccountRequestBody 
{
  public short id;
  public String name;
  public byte normal;
}
