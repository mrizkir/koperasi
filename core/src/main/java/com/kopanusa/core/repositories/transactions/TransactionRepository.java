package com.kopanusa.core.repositories.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kopanusa.core.models.transactions.TransactionModel;

public class TransactionRepository extends JpaRepository<TransactionModel, String>
{
  List<TransactionModel> findAll();
}
