package com.soccerbuddy.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * A web service error containing the complete context of
 * a problem encountered during the servicing.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@JsonDeserialize
@Builder
@Getter
@Accessors (fluent = true)
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
public final class ServiceError implements Serializable {
  private static final long serialVersionUID = -1338724449698535090L;
  
  public static final ServiceError NONE = 
      ServiceError.builder()
        .type(ServiceError.Type.NONE)
        .description("").build();
  
  @JsonProperty (value = "type", required = true) Type type;
  @JsonProperty (value = "description", required = true) String description;
  
  /**
   * An enumeration of all the service error types.
   * 
   * <p>
   * The codes follow the convention of prefixing the most appropriate
   * {@code HTTP} status code with a sequence number to tell codes corresponding
   * to a {@code HTTP} status apart.
   * 
   * @author mystarrocks
   * @since 1.0
   */
  @Getter
  @Accessors (fluent = true)
  public static enum Type {
    // Good ones
    /**
     * A non-error.
     */
    NONE(20001, ""),
    
    // Bad request errors
    /**
     * The error type indicating a validation failure of a {@code HTTP} request.
     */
    VALIDATION_ERROR(40001, "Validation failure error"),
    
    // Server errors
    /**
     * An error type indicating that something went terribly wrong on the server.
     */
    UNKNOWN_SERVER_ERROR(50001, "Unknown server error");
    
    private int code;
    private String message;
    
    Type(int code, String message) {
      this.code = code;
      this.message = message;
    }
  }
}