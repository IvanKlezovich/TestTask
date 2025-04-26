package com.example.testtask.exception;

import org.springframework.http.HttpStatus;

public class InvalidTransferException extends ApiException {

  public InvalidTransferException(String message, Object... args) {
    super(HttpStatus.BAD_REQUEST, message, args);
  }
}
