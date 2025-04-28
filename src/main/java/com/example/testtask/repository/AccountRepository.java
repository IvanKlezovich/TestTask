package com.example.testtask.repository;

import com.example.testtask.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

  @Modifying
  @Query("update Account a set a.balance = a.balance * 1.1 where a.balance * 1.1 <= a.startDeposit * 2.07")
  void updateBalance();
}
