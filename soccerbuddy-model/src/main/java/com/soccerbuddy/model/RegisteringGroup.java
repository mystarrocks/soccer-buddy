package com.soccerbuddy.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

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
@JsonDeserialize (builder = RegisteringGroup.RegisteringGroupBuilder.class)
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@Accessors (fluent = true)
public class RegisteringGroup implements Resource {
  
  @NotBlank
  @JsonProperty (value = "groupName", required = true)
  @Getter
  String groupName;
  
  @NotNull
  @JsonProperty (value = "admin", required = true)
  @Getter
  RegisteringUser admin;
  
  @JsonProperty (value = "existingUser", required = false)
  @Getter
  boolean existingUser;
  
  @JsonIgnore
  @NonFinal @Getter @Setter @Accessors (fluent = false)
  boolean audited;
  
  @JsonPOJOBuilder (withPrefix = "")
  public static final class RegisteringGroupBuilder {
    // an empty builder is needed to specify a JSON builder prefix of ""
    // would have been "with" otherwise
  }
}
