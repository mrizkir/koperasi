package com.kopanusa.core.repositories.master;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kopanusa.core.models.master.MasterAccountModel;

import java.util.List;

public interface MasterAccountRepository extends JpaRepository<MasterAccountModel, String>
{
  // List<MasterAccountModel> findAll(Limit limit);
  /**
   * Mencari account secara keseluruhan   
   * @return
  */
  List<MasterAccountModel> findAll();

  /**
   * Mendapat seluruh account berdasarkan id
   * @param id
   * @return
  */
  MasterAccountModel findById(Integer id);
  /**
   * Mencari account berdasarkan nama
   * @param name
   * @return
  */
  MasterAccountModel findByNameContainingIgnoreCase(String name);
}
