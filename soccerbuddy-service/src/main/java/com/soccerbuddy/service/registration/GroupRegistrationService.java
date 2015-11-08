package com.soccerbuddy.service.registration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccerbuddy.annotation.ServiceMethod;
import com.soccerbuddy.model.RegisteringGroup;
import com.soccerbuddy.model.RegisteringUser;
import com.soccerbuddy.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * The controller that handles group registration and un-registration activities.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@RestController
@RequestMapping ("/register")
@Slf4j
class GroupRegistrationService implements RegistrationService<RegisteringGroup> {
  
  @Autowired private UserRegistrationService userRegistrar;
  
  /**
   * Registers the given group by persisting them in the group data store.
   * 
   * <p>
   * If the user attempting to register is not registered with yet, they will be registered first
   * and should the registration go through successfully, the group will be registered.
   * 
   * @param registeringGroup  the registering group details
   * @return the result of registration
   */
  @Override
  @RequestMapping (value = "/group", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_REGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> register(@Valid @RequestBody RegisteringGroup registeringGroup) {
    if (registeringGroup.existingUser()) {
      // persist the registered group
    } else {
      RegisteringUser admin = registeringGroup.admin();
      userRegistrar.register(admin);
      // persist the registered group
    }
    Result<RegisteringGroup> result = Result.success(registeringGroup);
    return new ResponseEntity<Result<RegisteringGroup>>(result, HttpStatus.OK);
  }

  @Override
  @RequestMapping (value = "/group", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_UNREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> unregister(@RequestBody RegisteringGroup unregisteringGroup) {
    LOGGER.info("Group attempting to unregister: {}", unregisteringGroup);
    // persist the new status of the group
    Result<RegisteringGroup> result = Result.success(unregisteringGroup);
    return new ResponseEntity<Result<RegisteringGroup>>(result, HttpStatus.OK);
  }

  @Override
  @RequestMapping (value = "/group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_REREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> reregister(@RequestBody RegisteringGroup reregisteringGroup) {
    LOGGER.info("Group attempting to re-register: {}", reregisteringGroup);
    // persist the new status of the group
    Result<RegisteringGroup> result = Result.success(reregisteringGroup);
    return new ResponseEntity<Result<RegisteringGroup>>(result, HttpStatus.OK);
  }
  
}
