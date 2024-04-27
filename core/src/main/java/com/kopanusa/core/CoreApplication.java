package com.kopanusa.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kopanusa.core.controllers.setttings.UserRequestBody;
import com.kopanusa.core.services.settings.UserService;

import static com.kopanusa.core.models.auth.RoleEnum.MANAJER;;

@SpringBootApplication
public class CoreApplication 
{
  public static void main(String[] args) {
    SpringApplication.run(CoreApplication.class, args);
  }

  @Bean
	public CommandLineRunner commandLineRunner(UserService service)
  {
    return args -> {
      var admin = UserRequestBody.builder()
      .username("admin")
      .password("12345678")
      .namaLengkap("Mochammad Rizki Romdoni")
      .email("mr.romdoni@gmail.com")
      .nomorHP("081214553388")
      .role(MANAJER)
      .build();

      service.store(admin);

      System.out.println("Admin token: ");
    };
  }
}
