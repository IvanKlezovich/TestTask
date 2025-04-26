package com.example.testtask.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class ApiException extends ResponseStatusException {

    private final transient Object[] args;

    public ApiException(HttpStatus status, String message, Object... args) {
        super(status, message);
        this.args = args;
    }

    protected Map<String, Object> getResponseBodyFields(MessageSource messageSource, Locale locale) {
        String message;
        try {
            message = messageSource.getMessage(Objects.requireNonNull(getReason()), args, locale);
        } catch (NoSuchMessageException e) {
            message = getReason();
        }
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put("timestamp", new Date());
        fields.put("status", getStatusCode());
        fields.put("error", message);
        return fields;
    }

    public ResponseEntity<Map<String, Object>> toLocalResponseEntity(MessageSource messageSource, Locale locale) {
        return ResponseEntity
                .status(getStatusCode())
                .body(getResponseBodyFields(messageSource, locale));
    }
}
