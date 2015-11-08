package com.soccerbuddy.service.registration;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccerbuddy.annotation.ServiceMethod;
import com.soccerbuddy.model.RegisteringUser;
import com.soccerbuddy.model.Result;

/**
 * The web service that handles the user registration and unregistration activities.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@RestController
@RequestMapping ("/register")
class UserRegistrationService implements RegistrationService<RegisteringUser> {
  
  /**
   * Registers the given user by persisting them in the user data store.
   * 
   * @param registeringUser  the user attempting to register
   * @return the result of registration
   */
  @Override
  @RequestMapping (value = "/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "User registration", customFailureMarker = "USER_REGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringUser>> register(@Valid @RequestBody RegisteringUser registeringUser) {
    // persist the registered user
    Result<RegisteringUser> result = Result.success(registeringUser);
    return new ResponseEntity<Result<RegisteringUser>>(result, HttpStatus.OK);
  }

  /**
   * Unregisters the given user by updating the status of the registration in the 
   * user data store.
   * 
   * @param unregisteringUser  the entity attempting to unregister
   * @return the result of unregistration
   */
  @Override
  @RequestMapping (value = "/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "User unregistration", customFailureMarker = "USER_UNREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringUser>> unregister(@Valid @RequestBody RegisteringUser unregisteringUser) {
    // persist the new status of the registered user
    Result<RegisteringUser> result = Result.success(unregisteringUser);
    return new ResponseEntity<Result<RegisteringUser>>(result, HttpStatus.OK);
  }

  /**
   * Re-registers the given user by updating the status of the previous registration
   * in the user data store.
   * 
   * @param reregisteringEntity  the entity attempting to reregister
   * @return the result of reregistration
   */
  @Override
  @RequestMapping (value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "User reregistration", customFailureMarker = "USER_REREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringUser>> reregister(@Valid @RequestBody RegisteringUser reregisteringUser) {
    // persist the registered user
    Result<RegisteringUser> result = Result.success(reregisteringUser);
    return new ResponseEntity<Result<RegisteringUser>>(result, HttpStatus.OK);
  }
  
}
