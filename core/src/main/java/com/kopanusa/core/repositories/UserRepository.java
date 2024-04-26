package com.kopanusa.core.repositories;

import com.kopanusa.core.models.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author mrizkir
 */
public interface UserRepository extends JpaRepository<UserModel, Integer>
{
  Optional<UserModel> findByUsername(String username);
}
