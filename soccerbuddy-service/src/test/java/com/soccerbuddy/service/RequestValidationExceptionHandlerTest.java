package com.soccerbuddy.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.google.common.collect.Lists;
import com.soccerbuddy.exception.ServiceError;
import com.soccerbuddy.exception.ServiceError.Type;

/**
 * Test the {@link RequestValidationExceptionHandler}'s public API.
 * 
 * @author mystarrocks
 */
@RunWith (PowerMockRunner.class)
@PrepareForTest (MethodArgumentNotValidException.class)
public class RequestValidationExceptionHandlerTest {
  
  /**
   * Tests the contract {@code RequestValidationExceptionHandler} obeys.
   */
  @Test
  public void contract() {
    assertThat(RequestValidationExceptionHandler.class, is(typeCompatibleWith(ResourceExceptionHandler.class)));
  }
  
  /**
   * Tests the exception handling abilities of {@link RequestValidationExceptionHandler#handleException(MethodArgumentNotValidException)}.
   */
  @Test
  public void handleException() {
    ObjectError error1 = mock(ObjectError.class, "error1");
    ObjectError error2 = mock(ObjectError.class, "error2");
    ObjectError error3 = mock(ObjectError.class, "error3");
    
    BindingResult bindingResult = mock(BindingResult.class);
    
    MethodArgumentNotValidException exception = PowerMockito.mock(MethodArgumentNotValidException.class);
    
    when(exception.getBindingResult()).thenReturn(bindingResult);
    when(bindingResult.getAllErrors()).thenReturn(Lists.newArrayList(error1, error2, error3));
    when(error1.getDefaultMessage()).thenReturn("Validation of field1 failed");
    when(error2.getDefaultMessage()).thenReturn("Validation of field2 failed");
    when(error3.getDefaultMessage()).thenReturn("Validation of field3 failed");
    
    RequestValidationExceptionHandler handler = new RequestValidationExceptionHandler();
    ResponseEntity<ServiceError> result = handler.handleException(exception);
    
    assertThat(result.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    assertThat(result.getBody().type(), is(Type.VALIDATION_ERROR));
    assertThat(result.getBody().description(), is("Validation of field1 failed; "
        + "Validation of field2 failed; "
        + "Validation of field3 failed"));
  }
}
