package com.soccerbuddy.dataaccessor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests the {@link DataAccessorPreferences}'s public API.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@Ignore
public class DataAccessorPreferencesTest {
  
  /**
   * Tests the {@link DataAccessorPreferences#preferences()} method for successful execution.
   * 
   * <p>
   * The method is known to not throw any checked/unchecked exception and so fail under any circumstance.
   */
  @Test 
  public void preferences_Success() {
    Properties preferences = DataAccessorPreferences.preferences();
    String actualConnectionUrl = preferences.getProperty("soccerbuddy.cloud.mongo.url");
    
    assertThat(actualConnectionUrl, is("mongodb://soccer-buddy:soccer@ds052827.mongolab.com:52827/soccer-buddy"));
  }
  
}
