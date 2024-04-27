package com.kopanusa.core.repositories.auth;

import org.springframework.data.repository.CrudRepository;

import com.kopanusa.core.models.auth.UserModel;

import java.util.Optional;

/**
 *
 * @author mrizkir
 */
public interface UserRepository extends CrudRepository<UserModel, String>
{
  Optional<UserModel> findByUsername(String username);
}
