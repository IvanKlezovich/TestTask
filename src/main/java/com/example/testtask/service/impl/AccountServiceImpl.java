package com.example.testtask.service.impl;

import com.example.testtask.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountServiceImpl {

  private final AccountRepository accountRepository;

  @Transactional
  @Scheduled(fixedDelay = 30000)
  public void triggerBalanceUpdate() {
    accountRepository.updateBalance();
  }
}
