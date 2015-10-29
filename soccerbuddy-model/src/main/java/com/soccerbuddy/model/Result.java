package com.soccerbuddy.model;

import static com.soccerbuddy.model.ResponseCode.SUCCESS;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * The result of an action performed against a web service.
 * 
 * <p>
 * Includes a result code and a message, the resource that
 * was requested and optionally an error code and a message
 * that will help the clients decide on the next action in
 * case the resource could not be retrieved (or any of the
 * {@code CRUD} actions for that matter) successfully.
 * 
 * @param <R>  the resource requested by the client
 * 
 * @author mystarrocks
 * @since 1.0
 */
@JsonDeserialize (builder = Result.ResultBuilder.class)
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
@Accessors (fluent = true)
@ToString
public final class Result<R extends Resource> {
  @JsonProperty (value = "responseCode", required = true) int responseCode;
  @JsonProperty (value = "responseMessage", required = true) String responseMessage;
  @JsonProperty (value = "error", required = true) ServiceError error;
  @JsonProperty (value = "resource", required = true) R resource;
  
  /**
   * Returns a result indicating the success of the operation that
   * was attempted on the resource: <tt>resource</tt>.
   * 
   * @param resource  the resource the action was attempted on
   * @return a result indicating the success of the operation that
   * was attempted on the resource: <tt>resource</tt>
   */
  public static final <R extends Resource> Result<R> success(R resource) {
    return
        Result.<R>builder()
          .responseCode(SUCCESS.code())
          .responseMessage("The action on the resource was successful")
          .error(ServiceError.NONE)
          .resource(resource).build();
  }
  
  @JsonPOJOBuilder (withPrefix = "")
  public static final class ResultBuilder<R extends Resource> {
    // an empty builder is needed to specify a JSON builder prefix of ""
    // would have been "with"  otherwise
  }
}
