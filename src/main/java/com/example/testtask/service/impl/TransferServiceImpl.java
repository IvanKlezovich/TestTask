package com.example.testtask.service.impl;

import com.example.testtask.entity.Account;
import com.example.testtask.exception.EntityExistsException;
import com.example.testtask.exception.InvalidTransferException;
import com.example.testtask.repository.AccountRepository;
import com.example.testtask.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.locks.StampedLock;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);
  private final AccountRepository accountRepository;
  private final StampedLock balanceLock = new StampedLock();
  private static final BigDecimal MAX_TRANSFER_AMOUNT = new BigDecimal("1000000.00");
  private static final BigDecimal MIN_TRANSFER_AMOUNT = new BigDecimal("0.01");

  @Override
  public void transfer(Long userIdFrom, Long userIdTo, BigDecimal money) {
    log.info("Starting transfer from user {} to user {}: {}",
        userIdFrom, userIdTo, money);

    validateTransfer(userIdFrom, userIdTo, money);

    long stamp = balanceLock.writeLock();
    try {
      Account senderAccount = accountRepository.findById(userIdFrom)
              .orElseThrow(() -> new EntityExistsException("Отправитель не найден"));

      Account recipientAccount = accountRepository.findById(userIdTo)
              .orElseThrow(() -> new EntityExistsException("Получатель не найден"));

      validateBalance(senderAccount, money);

      senderAccount.setBalance(senderAccount.getBalance().subtract(money));
      recipientAccount.setBalance(recipientAccount.getBalance().add(money));

      accountRepository.save(senderAccount);
      accountRepository.save(recipientAccount);

      log.info("Transfer completed successfully");

    } finally {
      balanceLock.unlockWrite(stamp);
    }
  }

  private void validateTransfer(Long userIdFrom, Long userIdTo, BigDecimal money) {
    if (userIdFrom.equals(userIdTo)) {
      throw new InvalidTransferException("Нельзя перевести деньги самому себе");
    }

    if (money.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidTransferException("Сумма должна быть положительной");
    }

    if (money.compareTo(MAX_TRANSFER_AMOUNT) > 0) {
      throw new InvalidTransferException("Превышен максимальный лимит перевода");
    }

    if (money.compareTo(MIN_TRANSFER_AMOUNT) < 0) {
      throw new InvalidTransferException("Сумма меньше минимального лимита");
    }

    if (money.precision() > 15) {
      throw new InvalidTransferException("Превышена максимальная точность суммы");
    }
  }

  private void validateBalance(Account account, BigDecimal amount) {
    if (account.getBalance().compareTo(amount) < 0) {
      throw new InvalidTransferException("Недостаточно средств");
    }

    if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
      throw new InvalidTransferException("Отрицательный баланс счета");
    }
  }
}
