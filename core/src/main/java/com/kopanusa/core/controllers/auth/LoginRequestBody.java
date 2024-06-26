package com.kopanusa.core.controllers.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestBody 
{
  @NotEmpty(message = "Mohon username untuk disi")
  @NotBlank(message = "Mohon username untuk disi")
  private String username;

  @NotEmpty(message = "Mohon password untuk disi")
  @NotBlank(message = "Mohon password untuk disi")
  private String password;
}
