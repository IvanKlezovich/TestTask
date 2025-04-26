package com.example.testtask.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends ApiException {

  public AccountNotFoundException(HttpStatus status, String message, Object... args) {
    super(status, message, args);
  }
}
