package com.example.testtask.config;

import com.example.testtask.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

  private Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

  private static final String SERVER_ERROR = "Server error";

  private final MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException exception,
      Locale locale,
      HttpServletRequest request) {

    String error = exception.getBindingResult().getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.joining("; "));

    ApiException wrapper = new ApiException(HttpStatus.BAD_REQUEST, error,
        extractQueryPath(request));
    return wrapper.toLocalResponseEntity(messageSource, locale);
  }

  @ExceptionHandler({
      HttpMessageNotReadableException.class,
      MethodArgumentTypeMismatchException.class,
      MissingServletRequestParameterException.class,
      MissingPathVariableException.class,
      ConstraintViolationException.class,
      MissingRequestCookieException.class,
      NoResourceFoundException.class,
      HandlerMethodValidationException.class
  })
  public ResponseEntity<Map<String, Object>> handleBadRequest(Exception exception, Locale locale,
      HttpServletRequest request) {
    ApiException wrapper = new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage(),
        extractQueryPath(request));
    log.error(exception.getMessage(), exception);
    return wrapper.toLocalResponseEntity(messageSource, locale);
  }

  @ExceptionHandler({ApiException.class})
  public ResponseEntity<Map<String, Object>> handleApiExceptions(ApiException exception,
      Locale locale,
      HttpServletRequest request) {
    log.error(exception.getMessage(), exception);
    return exception.toLocalResponseEntity(messageSource, locale);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleServerError(Exception exception, Locale locale,
      HttpServletRequest request) {
    log.error(exception.getMessage(), exception);
    ApiException wrapper = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, SERVER_ERROR,
        extractQueryPath(request));
    return wrapper.toLocalResponseEntity(messageSource, locale);
  }

  @ExceptionHandler({
      HttpRequestMethodNotSupportedException.class,
      NoSuchElementException.class
  })
  public ResponseEntity<Map<String, Object>> handleRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      Locale locale,
      HttpServletRequest request) {
    ApiException wrapper = new ApiException(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(),
        extractQueryPath(request));
    log.error(ex.getMessage(), ex);
    return wrapper.toLocalResponseEntity(messageSource, locale);
  }

  private String extractQueryPath(HttpServletRequest request) {
    return Objects.nonNull(request.getQueryString())
        ? String.format("%s?%s", request.getRequestURI(), request.getQueryString())
        : request.getRequestURI();
  }
}
