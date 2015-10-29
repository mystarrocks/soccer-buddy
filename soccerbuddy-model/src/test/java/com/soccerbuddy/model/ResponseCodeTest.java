package com.soccerbuddy.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.soccerbuddy.model.ResponseCode;

/**
 * Tests the {@link ResponseCode}'s public API.
 * 
 * @author mystarrocks
 */
public class ResponseCodeTest {
  
  /**
   * Tests the instance controlled nature of the {@link ResponseCode}.
   */
  @Test
  public void instanceControl() {
    ResponseCode[] codes = ResponseCode.values();
    
    assertThat(codes, is(arrayContainingInAnyOrder(ResponseCode.ERROR, ResponseCode.SUCCESS, ResponseCode.WARNING)));
    
    assertThat(ResponseCode.SUCCESS.code(), is(0));
    assertThat(ResponseCode.WARNING.code(), is(1));
    assertThat(ResponseCode.ERROR.code(), is(9));
  }
}
