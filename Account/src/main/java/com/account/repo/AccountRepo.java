package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.account.entity.Accounts;

import jakarta.transaction.Transactional;


@Repository
public interface AccountRepo extends JpaRepository<Accounts,Long>{
      public Accounts findByCustomerId(Long customerId );
      
      
      @Transactional
      @Modifying
      public void deleteByCustomerId(Long customerId);
}
