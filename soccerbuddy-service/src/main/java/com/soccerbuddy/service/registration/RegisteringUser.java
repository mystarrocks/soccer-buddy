package com.soccerbuddy.service.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An individual registering user.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@Data @NoArgsConstructor class RegisteringUser {
  String username;
  String password;
  String emailId;
}
