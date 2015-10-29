package com.soccerbuddy.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

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
 * @see Result
 */
@JsonDeserialize (builder = RegisteringUser.RegisteringUserBuilder.class)
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
@ToString
@Accessors (fluent = true)
public class RegisteringUser implements Resource {
  
  @NotBlank
  @JsonProperty (value = "userName", required = true)
  @Getter
  String userName;
  
  @Email
  @NotBlank
  @Pattern (regexp = ".+@gmail\\.com")
  @JsonProperty (value = "emailId", required = true)
  @Getter
  String emailId;
  
  @Pattern (regexp = "\\(\\d{3}\\)-\\d{3}-\\d{4}")
  @JsonProperty (value = "phoneNumber", required = false)
  @Getter
  String phoneNumber;
  
  @JsonPOJOBuilder (withPrefix = "")
  public static final class RegisteringUserBuilder {
    // an empty builder is needed to specify a JSON builder prefix of ""
    // would have been "with"  otherwise
  }
}
