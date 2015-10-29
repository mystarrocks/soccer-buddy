package com.soccerbuddy.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soccerbuddy.model.ServiceError;

/**
 * Handles the exceptions thrown by the web service operations
 * (i.e the controllers).
 * 
 * <p>
 * These exception handlers cause the web service to respond with
 * an error object as opposed to the resource that the client
 * is looking for. This unfortunately leads to an ugly design on the
 * client where they are forced to expect one object or the other based
 * on the success or failure of the call (say, based on the HTTP status
 * code), but there's no cleaner way to do it now (watch 
 * <a href="https://jira.spring.io/browse/SPR-10961">SPR-10961</a>).
 * 
 * @param <E>  the exception this handler deals with
 * 
 * @author mystarrocks
 * @since 1.0
 * @see https://jira.spring.io/browse/SPR-10961
 */
public interface ResourceExceptionHandler<E> {
  
  /**
   * Handles the given exception producing a {@code JSON} content
   * of an error object containing the complete context of the failure.
   * 
   * @param e  the encountered exception
   * @return a response containing the complete failure context
   */
  @ExceptionHandler @ResponseBody ResponseEntity<ServiceError> handleException(E e);
}
