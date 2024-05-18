package com.kopanusa.core.repositories.master;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kopanusa.core.models.master.MasterAccountModel;

import java.util.List;

public interface MasterAccountRepository extends JpaRepository<MasterAccountModel, String>
{
  List<MasterAccountModel> findAll();
}
