package com.soccerbuddy.service.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * An individual registering user.
 * 
 * <p>
 * The user will attempt to register through the {@code /register/user}
 * API. The registering user can optionally choose to create a group
 * <em>and</em> register themselves in which case they should 
 * use the {@code /register/group} API.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see RegisteringGroup
 */
@Value
@Accessors (fluent = true)
@Builder
@JsonDeserialize (builder = RegisteringUser.RegisteringUserBuilder.class)
class RegisteringUser {
  @JsonProperty (value = "userName", required = true) String userName;
  @JsonProperty (value = "password", required = true) String password;
  @JsonProperty (value = "emailId", required = true) String emailId;
  @JsonProperty (value = "phoneNumber", required = false) String phoneNumber;
  
  @JsonPOJOBuilder (withPrefix = "")
  static final class RegisteringUserBuilder {
    // an empty builder is needed to specify a JSON builder prefix of ""; would have been "with"  otherwise
  }
}
