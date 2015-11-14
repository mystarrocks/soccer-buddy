package com.soccerbuddy.model;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * An enumeration of all the user registration statuses.
 * 
 * @author mystarrocks
 * @since 1.0
 */
public enum UserRegistrationStatus {
  /**
   * An active registered user that has never attempted
   * to unregister before.
   */
  ACTIVE (0),
  
  /**
   * An active registered user that has unregistered at least
   * once in the past.
   */
  ACTIVE_AGAIN (1),
  
  /**
   * An inactive/unsubscribed user that has never done so
   * more than once before.
   */
  INACTIVE (2),
  
  /**
   * An inactive/unsubscribed user that has done so
   * more than once before.
   */
  INACTIVE_AGAIN (3),
  
  /**
   * An unknown user that has never registered/unregistered
   * before.
   * 
   * <p>
   * The default user registration status if it's not anything
   * else.
   */
  UNKNOWN (4);
  
  private int code;
  private static final Stream<UserRegistrationStatus> VALUE_STREAM = Arrays.stream(UserRegistrationStatus.values());
  
  private UserRegistrationStatus(int code) {
    this.code = code;
  }
  
  /**
   * Returns the numerical code associated with the status.
   * 
   * @return the numerical code associated with the status
   */
  public int code() {
    return this.code;
  }
  
  /**
   * Returns the status instance associated with the given
   * numerical code.
   * 
   * @param code  the numerical code a {@code UserRegistrationStatus}
   * is likely to be associated with
   * @return the status instance associated with the given
   * numerical code
   */
  public static UserRegistrationStatus ofCode(int code) {
    return VALUE_STREAM
        .filter(status -> status.code() == code)
        .findFirst()
        .orElse(UNKNOWN);
  }
}
