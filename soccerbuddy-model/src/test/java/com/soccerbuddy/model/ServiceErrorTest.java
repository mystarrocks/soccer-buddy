package com.soccerbuddy.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.soccerbuddy.model.ServiceError;

/**
 * Tests the {@link ServiceError}'s public API.
 * 
 * @author mystarrocks
 */
public class ServiceErrorTest {
  
  /**
   * Tests the instance controlled nature of {@link ServiceError#type()}.
   */
  @Test
  public void instanceControl_Types() {
    ServiceError.Type[] types = ServiceError.Type.values();
    
    assertThat(types, arrayContainingInAnyOrder(ServiceError.Type.NONE, ServiceError.Type.VALIDATION_ERROR, ServiceError.Type.UNKNOWN_SERVER_ERROR));
    
    assertThat(ServiceError.Type.NONE.code(), is(20001));
    assertThat(ServiceError.Type.NONE.message(), is(""));
    
    assertThat(ServiceError.Type.VALIDATION_ERROR.code(), is(40001));
    assertThat(ServiceError.Type.VALIDATION_ERROR.message(), is("Validation failure error"));
    
    assertThat(ServiceError.Type.UNKNOWN_SERVER_ERROR.code(), is(50001));
    assertThat(ServiceError.Type.UNKNOWN_SERVER_ERROR.message(), is("Unknown server error"));
  }
  
  /**
   * Tests the {@link ServiceError#NONE} for a non-error instance.
   */
  @Test
  public void none() {
    ServiceError none = ServiceError.NONE;
    
    assertThat(none.type(), is(ServiceError.Type.NONE));
    assertThat(none.description(), is(""));
  }
}