package com.kopanusa.core.repositories.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kopanusa.core.models.auth.TokenModel;

public interface TokenRepository extends JpaRepository<TokenModel, Integer> 
{
  @Query(value = """
      SELECT t.* FROM token t INNER JOIN users u\s
      on t.user_id = u.id\s
      WHERE u.id = :id AND (t.expired = false or t.revoked = false)\s
      """, nativeQuery = true)
  List<TokenModel> findAllValidTokenByUser(String id);

  Optional<TokenModel> findByToken(String token);
}
