package com.kopanusa.core.repositories;

import com.kopanusa.core.models.TokenModel;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenModel, Integer> 
{
  @Query(value = """
      select t from Token t inner join User u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<TokenModel> findAllValidTokenByUser(String id);

  Optional<TokenModel> findByToken(String token);
}
