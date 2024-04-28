package com.kopanusa.core.models.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mrizkir
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails
{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(length = 36)
  private String id;
  
  @Column(unique = true, length = 50, nullable = false)
  private String username;
  
  @Column(length = 100, nullable = false)
  private String password;
  
  @Column(length = 150)
  private String nama_lengkap;
  
  @Column(unique = true, length = 150, nullable = false)
  private String email;
  
  @Column(length = 12)
  private String nomor_hp;
  
  @Enumerated(EnumType.STRING)
  private RoleEnum role;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime created_at;
  
  @UpdateTimestamp
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updated_at;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() 
  {
    // return List.of(new SimpleGrantedAuthority(role.name()));
    return List.of();
  }
  
  @Override
  public String getUsername()
  {
    return username;
  }
  
  @Override
  public String getPassword()
  {
    return password;
  }
  
  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }
}
