package com.soccerbuddy.service.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * A registering group.
 * 
 * <p>
 * The person attempting to register a group may or may not be registered
 * yet. If they are not registered yet, the person will be registered first
 * and then the group will be created with he/she as the administrator.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see RegisteringUser
 */
@Value
@Accessors (fluent = true)
@Builder
@JsonDeserialize (builder = RegisteringGroup.RegisteringGroupBuilder.class)
class RegisteringGroup {
  @JsonProperty (value = "groupName", required = true) String groupName;
  @JsonProperty (value = "admin", required = true) RegisteringUser admin;
  @JsonProperty (value = "existingUser", required = false) boolean existingUser;
  
  @JsonPOJOBuilder (withPrefix = "")
  static final class RegisteringGroupBuilder {
    // an empty builder is needed to specify a JSON builder prefix of ""
    // would have been "with" otherwise
  }
}
