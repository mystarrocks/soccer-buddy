package com.soccerbuddy.exception;

import org.apache.commons.lang3.exception.ContextedException;

/**
 * A contexted exception indicating a failure during querying/persisting
 * data from/into the data store.
 * 
 * @author mystarrocks
 * @since 1.0
 */
public class DataStoreException extends ContextedException {
  
  private static final long serialVersionUID = 8238079151248665425L;

  /**
   * Creates a data store exception with the given message.
   * 
   * @param message  the message to be set on the exception
   */
  public DataStoreException(String message) {
    super(message);
  }
  
  /**
   * Creates a data store exception with the given message.
   * 
   * @param cause  the root cause of the failure
   */
  public DataStoreException(Throwable cause) {
    super(cause);
  }
  
  /**
   * Creates a data store exception with the given message and
   * root cause.
   * 
   * @param message  the message to be set on the exception
   * @param cause  the root cause of the failure
   */
  public DataStoreException(String message, Throwable cause) {
    super(message, cause);
  }
  
  @Override
  public DataStoreException addContextValue(String label, Object value) {
    return (DataStoreException) super.addContextValue(label, value);
  }
}
