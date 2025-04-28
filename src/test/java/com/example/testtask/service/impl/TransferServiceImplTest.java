package com.example.testtask.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.testtask.entity.Account;
import com.example.testtask.entity.User;
import com.example.testtask.exception.EntityExistsException;
import com.example.testtask.exception.InvalidTransferException;
import com.example.testtask.repository.AccountRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private TransferServiceImpl transferService;

  private Account senderAccount;
  private Account recipientAccount;

  @BeforeEach
  void setUp() {
    senderAccount = new Account(
        1L,
        User.builder().id(1L).build(),
        new BigDecimal("1000.00"),
        new BigDecimal("1000.00")
    );

    recipientAccount = new Account(
        2L,
        User.builder().id(2L).build(),
        new BigDecimal("500.00"),
        new BigDecimal("500.00")
    );

    lenient().when(accountRepository.findById(eq(1L)))
        .thenReturn(java.util.Optional.of(senderAccount));
    lenient().when(accountRepository.findById(eq(2L)))
        .thenReturn(java.util.Optional.of(recipientAccount));
  }

  @Test
  void transferShouldCompleteSuccessfully() throws EntityExistsException {
    when(accountRepository.save(any(Account.class))).thenAnswer(
        invocation -> invocation.getArgument(0));
    transferService.transfer(1L, 2L, new BigDecimal("100.00"));

    assertEquals(new BigDecimal("900.00"), senderAccount.getBalance());
    assertEquals(new BigDecimal("600.00"), recipientAccount.getBalance());

    verify(accountRepository, times(2)).save(any(Account.class));
  }

  @Test
  void transferShouldThrowExceptionWhenSenderNotFound() {
    when(accountRepository.findById(eq(999L))).thenReturn(java.util.Optional.empty());

    assertThrows(EntityExistsException.class, () ->
        transferService.transfer(999L, 2L, new BigDecimal("100.00"))
    );
  }

  @Test
  void transferShouldThrowExceptionWhenRecipientNotFound() {
    when(accountRepository.findById(eq(2L))).thenReturn(java.util.Optional.empty());

    assertThrows(EntityExistsException.class, () ->
        transferService.transfer(1L, 2L, new BigDecimal("100.00"))
    );
  }

  @Test
  void transferShouldThrowExceptionWhenSameUser() {
    assertThrows(InvalidTransferException.class, () ->
        transferService.transfer(1L, 1L, new BigDecimal("100.00"))
    );
  }

  @Test
  void transferShouldThrowExceptionWhenAmountLessThanMin() {
    assertThrows(InvalidTransferException.class, () ->
        transferService.transfer(1L, 2L, new BigDecimal("0.009"))
    );
  }

  @Test
  void transferShouldThrowExceptionWhenAmountGreaterThanMax() {
    assertThrows(InvalidTransferException.class, () ->
        transferService.transfer(1L, 2L, new BigDecimal("1000001.00"))
    );
  }

  @Test
  void transferShouldThrowExceptionWhenInsufficientBalance() {
    senderAccount.setBalance(new BigDecimal("50.00"));

    assertThrows(InvalidTransferException.class, () ->
        transferService.transfer(1L, 2L, new BigDecimal("100.00"))
    );
  }

  @Test
  void transferShouldMaintainThreadSafety() throws InterruptedException {
    when(accountRepository.save(any(Account.class))).thenAnswer(
        invocation -> invocation.getArgument(0));
    Thread thread1 = new Thread(() -> {
      try {
        transferService.transfer(1L, 2L, new BigDecimal("300.00"));
      } catch (EntityExistsException e) {
        fail("Не должно быть исключений");
      }
    });

    Thread thread2 = new Thread(() -> {
      try {
        transferService.transfer(1L, 2L, new BigDecimal("400.00"));
      } catch (EntityExistsException e) {
        fail("Не должно быть исключений");
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    assertEquals(new BigDecimal("300.00"), senderAccount.getBalance());
  }
}
