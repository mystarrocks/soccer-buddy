package com.soccerbuddy.model;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * An enumeration of all the codes the responses
 * produced by the services can contain.
 * 
 * <p>
 * Calling the {@code code()} on these instances will get
 * you the numerical code associated with them.
 * 
 * @author mystarrocks
 * @since 1.0
 */
public enum ResponseCode {
  /**
   * A successful response.
   */
  SUCCESS (0),
  
  /**
   * A response that did not error out, but didn't
   * succeed either, thereby necessitating a message
   * to be communicated to the user about the event.
   */
  WARNING (1),
  
  /**
   * A failed response.
   */
  ERROR (9);
  
  @Getter @Accessors (fluent = true)
  private int code;
  
  private ResponseCode(int code) {
    this.code = code;
  }
}
