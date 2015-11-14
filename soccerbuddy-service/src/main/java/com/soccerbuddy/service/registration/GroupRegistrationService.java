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
import com.soccerbuddy.data.DataStoreException;
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
   * @throws DataStoreException if an invalid set of data was passed in to perform registration
   * (like, say, an already registered and active group) 
   */
  @Override
  @RequestMapping (value = "/group", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_REGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> register(@Valid @RequestBody RegisteringGroup registeringGroup) throws DataStoreException {
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

  /**
   * Unregisters the given user by updating the status of the registration in the 
   * group data store.
   * 
   * @param unregisteringGroup  the entity attempting to unregister
   * @return the result of unregistration
   * @throws DataStoreException if an invalid set of data was passed in to perform unregistration
   * (like, say, an already unregistered and inactive group)
   */
  @Override
  @RequestMapping (value = "/group", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_UNREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> unregister(@RequestBody RegisteringGroup unregisteringGroup) throws DataStoreException {
    LOGGER.info("Group attempting to unregister: {}", unregisteringGroup);
    // persist the new status of the group
    Result<RegisteringGroup> result = Result.success(unregisteringGroup);
    return new ResponseEntity<Result<RegisteringGroup>>(result, HttpStatus.OK);
  }

  /**
   * Re-registers the given group by updating the status of the previous registration
   * in the group data store.
   * 
   * @param reregisteringGroup  the group attempting to reregister
   * @return the result of reregistration
   * @throws DataStoreException if an invalid set of data was passed in to perform reregistration
   * (like, say, an already registered and active group)
   */
  @Override
  @RequestMapping (value = "/group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ServiceMethod (action = "Group registration", customFailureMarker = "GROUP_REREGISTRATION_FAILURE")
  public @ResponseBody ResponseEntity<Result<RegisteringGroup>> reregister(@RequestBody RegisteringGroup reregisteringGroup) throws DataStoreException {
    LOGGER.info("Group attempting to re-register: {}", reregisteringGroup);
    // persist the new status of the group
    Result<RegisteringGroup> result = Result.success(reregisteringGroup);
    return new ResponseEntity<Result<RegisteringGroup>>(result, HttpStatus.OK);
  }
  
}
