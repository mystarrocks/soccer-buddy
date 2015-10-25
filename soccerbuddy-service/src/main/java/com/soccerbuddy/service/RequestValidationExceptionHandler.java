package com.soccerbuddy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soccerbuddy.service.ServiceError.Type;

/**
 * Handles all the failed request validations resulting in
 * {@link MethodArgumentNotValidException}s.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see https://jira.spring.io/browse/SPR-10961
 */
class RequestValidationExceptionHandler implements ResourceExceptionHandler<MethodArgumentNotValidException> {
  
  @ExceptionHandler
  @ResponseBody
  @Override
  public ResponseEntity<ServiceError> handleException(MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getBindingResult().getAllErrors();
    String description = 
        errors.stream()
          .map(ObjectError :: getDefaultMessage)
          .collect(Collectors.joining("; "));
    ServiceError error = 
        ServiceError.builder()
          .type(Type.VALIDATION_ERROR)
          .description(description).build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}