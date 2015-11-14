package com.soccerbuddy.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
 * @see Result
 */
@JsonDeserialize (builder = RegisteringUser.RegisteringUserBuilder.class)
@Entity
@NoArgsConstructor (access = AccessLevel.PRIVATE)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
@Builder
@Accessors (fluent = true)
@ToString
public class RegisteringUser implements Resource {
  
  @NotBlank
  @JsonProperty (value = "userName", required = true)
  @Id
  @Getter
  private String userName;
  
  @Email
  @NotBlank
  @Pattern (regexp = ".+@gmail\\.com")
  @JsonProperty (value = "emailId", required = true)
  @Index
  @Getter
  private String emailId;
  
  @Pattern (regexp = "\\(\\d{3}\\)-\\d{3}-\\d{4}")
  @JsonProperty (value = "phoneNumber", required = false)
  @Getter
  private String phoneNumber;
  
  @JsonProperty (value = "status", required = false)
  @Index
  @Getter @Setter
  private UserRegistrationStatus status;
  
  @JsonIgnore
  @Ignore
  @Getter @Setter @Accessors (fluent = false)
  private boolean audited;
  
  /**
   * The builder utility that helps build instances of the [enclosing] type:
   * {@link RegisteringUser}.
   * 
   * @author mystarrocks
   * @since 1.0
   */
  @JsonPOJOBuilder (withPrefix = "")
  public static final class RegisteringUserBuilder {
    // an empty builder is needed to specify a JSON builder prefix of ""
    // would have been "with" otherwise
  }
}
