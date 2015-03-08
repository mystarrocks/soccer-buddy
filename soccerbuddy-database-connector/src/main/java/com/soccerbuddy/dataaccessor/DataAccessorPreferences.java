package com.soccerbuddy.dataaccessor;

import java.io.IOException;
import java.util.Properties;

/**
 * Manages all the preferences maintained in the resource bundle needed for the 
 * application classes.
 * 
 * <p>
 * The preferences will be read from the resource bundle named: {@code SoccerbuddyDataAccessor.properties}.
 * 
 * @author mystarrocks
 * @since 1.0
 */
public class DataAccessorPreferences {
  private static final Properties preferences = new Properties();
  
  static {
    try {
      preferences.load(DataAccessorPreferences.class.getResourceAsStream("/SoccerbuddyDataAccessor.properties"));
    } catch (IOException e) {
      // would just never happen
      e.printStackTrace();
    }
  }
  
  /**
   * Gets you a fully loaded set of preferences from the config file: {@code SoccerbuddyDataAccessor.properties}.
   * 
   * @return a fully loaded set of preferences from the config file: {@code SoccerbuddyDataAccessor.properties}
   */
  public static final Properties preferences() {
    return preferences;
  }
}
