package com.soccerbuddy.service.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * The controller that handles user and group registration/un-registration related activities.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@RestController
@RequestMapping ("/register")
@Slf4j
class RegistrationService {
  
  /**
   * Registers the given user by persisting them in the user data store.
   * 
   * @param registeringUser  the user attempting to register
   * @return the result of registration
   */
  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<RegisteringUser> registerUser(@RequestBody RegisteringUser registeringUser) {
    // persist the registered user
    log.info("User attempting to register: {}", registeringUser);
    return new ResponseEntity<RegisteringUser>(registeringUser, HttpStatus.OK);
  }
  
  /**
   * Registers the given group by persisting them in the group data store.
   * 
   * <p>
   * If the user attempting to create is not registered with yet, they will be registered first
   * and should the registration go through successfully, the group will be registered.
   * 
   * @param registeringGroup  the registering group details
   * @return the result of registration
   */
  @RequestMapping(value = "/group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity<RegisteringGroup> registerGroup(@RequestBody RegisteringGroup registeringGroup) {
    // persist the registered user
    log.info("Group attempting to register: {}", registeringGroup);
    if (registeringGroup.existingUser()) {
      // persist the registered group
    } else {
      RegisteringUser admin = registeringGroup.admin();
      registerUser(admin);
      // persist the registered group
    }
    return new ResponseEntity<RegisteringGroup>(registeringGroup, HttpStatus.OK);
  }
}