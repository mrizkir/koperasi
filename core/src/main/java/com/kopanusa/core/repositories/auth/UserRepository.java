package com.kopanusa.core.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kopanusa.core.models.auth.UserModel;

import java.util.Optional;

/**
 *
 * @author mrizkir
 */
public interface UserRepository extends JpaRepository<UserModel, String>
{
  Optional<UserModel> findByUsername(String username);
}
