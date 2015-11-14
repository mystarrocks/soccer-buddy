package com.soccerbuddy.service;

import static com.soccerbuddy.exception.LogMarker.VALIDATION_FAILURE;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccerbuddy.exception.LogMarker;
import com.soccerbuddy.exception.ServiceError;
import com.soccerbuddy.exception.ServiceError.Type;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles all the failed web service request validations resulting in
 * {@link MethodArgumentNotValidException}s.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see <a href="https://jira.spring.io/browse/SPR-10961">SPR-10961</a>
 */
@ControllerAdvice (annotations = RestController.class)
@Slf4j
public class RequestValidationExceptionHandler implements ResourceExceptionHandler<MethodArgumentNotValidException> {
  private static final Marker MARKER_VALIDATION_FAILURE = LogMarker.asSlf4jMarker(VALIDATION_FAILURE);
  
  @ExceptionHandler (value = MethodArgumentNotValidException.class)
  @ResponseBody
  @Override
  public ResponseEntity<ServiceError> handleException(MethodArgumentNotValidException e) {
    LOGGER.error(MARKER_VALIDATION_FAILURE, "Request validation failed.", e);
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
