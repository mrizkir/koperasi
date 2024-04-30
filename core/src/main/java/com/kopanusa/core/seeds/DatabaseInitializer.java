package com.kopanusa.core.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kopanusa.core.controllers.setttings.UserRequestBody;
import com.kopanusa.core.services.settings.UserService;
import static com.kopanusa.core.models.auth.RoleEnum.MANAJER;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile("development")
public class DatabaseInitializer  implements CommandLineRunner
{
  private final UserService service;

  @Override
  public void run(String... args) throws Exception 
  {
    var admin = UserRequestBody.builder()
      .username("admin")
      .password("12345678")
      .namaLengkap("Mochammad Rizki Romdoni")
      .email("mr.romdoni@gmail.com")
      .nomorHP("081214553388")
      .role(MANAJER)
      .build();

      service.store(admin);

      System.out.println("Admin user created !!!");
  }
}
