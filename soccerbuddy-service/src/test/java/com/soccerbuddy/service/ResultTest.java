package com.soccerbuddy.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Tests the {@link Result}'s public API.
 * 
 * @author mystarrocks
 */
public class ResultTest {
  
  /**
   * Tests the {@link Result#success(Resource)} for a successful
   * response.
   */
  @Test
  public void success() {
    Resource resource = mock(Resource.class);
    
    Result<? extends Resource> result = Result.success(resource);
    
    assertThat(result.responseCode(), is(ResponseCode.SUCCESS.code()));
    assertThat(result.responseMessage(), is("The action on the resource was successful"));
    assertThat(result.error(), is(ServiceError.NONE));
    assertThat(result.resource(), is(resource));
  }
}
