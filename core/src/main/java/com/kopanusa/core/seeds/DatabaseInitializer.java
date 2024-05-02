package com.kopanusa.core.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.kopanusa.core.controllers.setttings.UserRequestBody;
import com.kopanusa.core.services.dmaster.MasterAccountService;
import com.kopanusa.core.services.settings.UserService;
import static com.kopanusa.core.models.auth.RoleEnum.MANAJER;

import java.util.ArrayList;
import java.util.HashMap;

import com.kopanusa.core.controllers.dmaster.MasterAccountRequestBody;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile("development")
public class DatabaseInitializer  implements CommandLineRunner
{
  private final UserService userService;
  private final MasterAccountService accountService;

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

    userService.store(admin);
    System.out.println("Admin user created !!!");

    // var accounts = MasterAccountRequestBody.builder()
    // .id((short)100)
    // .name("Assets")
    // .normal((byte)1)
    // .build();

    ArrayList<HashMap> accounts = new ArrayList<>();
    HashMap<String, Object> account = new HashMap<String, Object>();

    account.put("id", (short)100);
    account.put("name", "Assets");
    account.put("normal", (byte)1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)110);
    account.put("name", "Cash");
    account.put("normal", (byte)1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)120);
    account.put("name", "Merchandise");
    account.put("normal", (byte)1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)210);
    account.put("name", "Deferred Revenue");
    account.put("normal", (byte)-1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)300);
    account.put("name", "Revenues");
    account.put("normal", (byte)-1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)400);
    account.put("name", "Expenses");
    account.put("normal", (byte)1);
    accounts.add(account);
    
    account = new HashMap<String, Object>();
    account.put("id", (short)410);
    account.put("name", "Cost of Goods Sold");
    account.put("normal", (byte)1);
    accounts.add(account);
    
    account = new HashMap<String, Object>();
    account.put("id", (short)500);
    account.put("name", "Equity");
    account.put("normal", (byte)-1);
    accounts.add(account);

    account = new HashMap<String, Object>();
    account.put("id", (short)510);
    account.put("name", "Capital");
    account.put("normal", (byte)-1);
    accounts.add(account);

    // var data_akun;
    for(int i = 0; i < accounts.size(); i++)
    {
      short id = (short) accounts.get(i).get("id");
      String name = (String) accounts.get(i).get("name");
      byte normal = (byte) accounts.get(i).get("normal");

      var data_akun = MasterAccountRequestBody.builder()
      .id(id)
      .name(name)
      .normal(normal)
      .build();

      accountService.store(data_akun);

      System.out.println("Account " + name + " created !!!");
    }   

  }
}
