package com.kopanusa.core.controllers.setttings;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.kopanusa.core.models.auth.RoleEnum;

import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody 
{
  private String username;
  private String password;
  private String namaLengkap;
  private String email;
  private String nomorHP;
  private RoleEnum role;
}
