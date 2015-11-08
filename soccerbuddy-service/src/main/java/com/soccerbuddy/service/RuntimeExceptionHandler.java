package com.soccerbuddy.service;

import static com.soccerbuddy.model.LogMarker.*;

import org.slf4j.Marker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccerbuddy.model.LogMarker;
import com.soccerbuddy.model.ServiceError;
import com.soccerbuddy.model.ServiceError.Type;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles all the failed request validations resulting in
 * {@link MethodArgumentNotValidException}s.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see https://jira.spring.io/browse/SPR-10961
 */
@ControllerAdvice (annotations = RestController.class)
@Slf4j
public class RuntimeExceptionHandler implements ResourceExceptionHandler<RuntimeException> {
  private static final Marker MARKER_UNKNOWN_ERROR = LogMarker.asSlf4jMarker(UNKNOWN_ERROR);
  
  @ExceptionHandler (value = NullPointerException.class)
  @ResponseBody
  @Override
  public ResponseEntity<ServiceError> handleException(RuntimeException e) {
    LOGGER.error(MARKER_UNKNOWN_ERROR, "An uncaught exception was thrown during one of the requests. ", e);
    ServiceError error = 
        ServiceError.builder()
          .type(Type.UNKNOWN_SERVER_ERROR)
          .description("Something went wrong on the server: " + e.getMessage()).build();
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
