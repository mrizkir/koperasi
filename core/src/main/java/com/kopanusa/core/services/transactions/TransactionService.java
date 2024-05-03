package com.kopanusa.core.services.transactions;

import org.springframework.stereotype.Service;

import com.kopanusa.core.repositories.transactions.TransactionRepository;

import lombok.RequiredArgsConstructor;

// @Service
@RequiredArgsConstructor
public class TransactionService 
{
  private final TransactionRepository repository;


}
