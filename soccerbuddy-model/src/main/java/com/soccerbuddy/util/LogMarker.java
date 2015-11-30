package com.soccerbuddy.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * An enumeration of all the application wide logging markers.
 * 
 * <p>
 * Also supports obtaining a {@code Slf4j} marker from custom marker 
 * names (see {@link #asSlf4jMarker(String)}).
 * 
 * @author mystarrocks
 * @since 1.0
 */
public enum LogMarker {
  
  /**
   * Marks the method execution timings.
   */
  TIMES,
  
  /**
   * Marks all the input request validation failures.
   */
  VALIDATION_FAILURE,
  
  /**
   * Marks all the unknown runtime exceptions.
   */
  UNKNOWN_ERROR;
  
  /**
   * Retrieves and returns a usable {@code Slf4j} marker given an appropriate 
   * marker instance.
   * 
   * @param marker  the log marker
   * @return a usable marker given an appropriate instance; that corresponding
   * to {@code UNKNOWN_ERROR} if <tt>marker</tt> was {@code null}
   */
  public static Marker asSlf4jMarker(LogMarker marker) {
    if (marker == null) {
      return MarkerFactory.getMarker(UNKNOWN_ERROR.name());
    }
    return MarkerFactory.getMarker(marker.name());
  }
  
  /**
   * Retrieves and returns a usable {@code Slf4j} marker given an appropriate 
   * marker name.
   * 
   * @param marker  the log marker name
   * @return a usable marker given an appropriate marker name; that corresponding
   * to {@code UNKNOWN_ERROR} if <tt>marker</tt> was {@code null} or empty
   */
  public static Marker asSlf4jMarker(String marker) {
    if (marker == null || "".equals(marker)) {
      return MarkerFactory.getMarker(UNKNOWN_ERROR.name());
    }
    return MarkerFactory.getMarker(marker);
  }
  
}
