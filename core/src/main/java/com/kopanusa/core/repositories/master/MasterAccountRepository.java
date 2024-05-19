package com.kopanusa.core.repositories.master;

import com.kopanusa.core.models.master.MasterAccountModel;

import java.util.List;

public interface MasterAccountRepository
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
  MasterAccountModel findById(String id);
  /**
   * Mencari account berdasarkan nama
   * @param name
   * @return
  */
  MasterAccountModel findByName(String name);
}
