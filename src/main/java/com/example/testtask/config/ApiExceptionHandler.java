package com.example.testtask.config;

import com.example.testtask.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(Exception exception, Locale locale, HttpServletRequest request) {
        ApiException wrapper = new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage(),
                extractQueryPath(request));
        return wrapper.toLocalResponseEntity(messageSource, locale);
    }

    private String extractQueryPath(HttpServletRequest request) {
        return Objects.nonNull(request.getQueryString())
                ? String.format("%s?%s", request.getRequestURI(), request.getQueryString())
                : request.getRequestURI();
    }
}
