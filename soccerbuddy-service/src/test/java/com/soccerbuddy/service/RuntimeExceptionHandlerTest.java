package com.soccerbuddy.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Tests the {@link RuntimeExceptionHandler}'s public API.
 * 
 * @author mystarrocks
 */
public class RuntimeExceptionHandlerTest {
  
  /**
   * Tests the {@link RuntimeExceptionHandler#handleException(Exception)}
   * for a generic uncaught exception.
   */
  @Test
  public void handleException() {
    RuntimeException e = new NullPointerException("What the actual heck?");
    
    RuntimeExceptionHandler handler = new RuntimeExceptionHandler();
    ResponseEntity<ServiceError> result = handler.handleException(e);
    
    assertThat(result.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    assertThat(result.getBody().type(), is(ServiceError.Type.UNKNOWN_SERVER_ERROR));
    assertThat(result.getBody().description(), is("Something went wrong on the server: " + e.getMessage()));
  }
}
