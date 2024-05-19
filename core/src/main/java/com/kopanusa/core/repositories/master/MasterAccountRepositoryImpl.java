package com.kopanusa.core.repositories.master;

import java.util.List;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import com.kopanusa.core.models.master.MasterAccountModel;

@Repository
@RequiredArgsConstructor
public class MasterAccountRepositoryImpl implements MasterAccountRepository
{
  private final EntityManager entityManager;

  @Override
  public List<MasterAccountModel> findAll() {
    TypedQuery<MasterAccountModel> query = entityManager.createQuery("FROM MasterAccountModel",MasterAccountModel.class);
    return query.getResultList();
  }

  @Override
  public MasterAccountModel findById(String id) {
    return entityManager.find(MasterAccountModel.class, id);
  }

  @Override
  public MasterAccountModel findByName(String name)
  {
    try 
    {
      TypedQuery<MasterAccountModel> query = entityManager.createQuery("FROM MasterAccountModel WHERE name=:name",MasterAccountModel.class);
      query.setParameter("name", name);

      return query.getSingleResult();  
    } 
    catch (Exception e)
    {
      return null;
    }
  }
  
}
