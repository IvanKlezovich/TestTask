package com.example.testtask.exception;

import org.springframework.http.HttpStatus;

public class EntityExistsException extends ApiException {
    public EntityExistsException(HttpStatus status, String message, Object... args) {
        super(status, message, args);
    }

    public EntityExistsException(String message, Object... args) {
        super(HttpStatus.BAD_REQUEST, message, args);
    }
}
