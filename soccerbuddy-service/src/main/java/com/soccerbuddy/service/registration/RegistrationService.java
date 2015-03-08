package com.soccerbuddy.service.registration;

import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The webservice controller that handles user registration related activities.
 * 
 * @author mystarrocks
 */
@RestController
class RegistrationService {
  
  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<RegisteringUser> registerUser(@RequestBody RegisteringUser registeringUser) throws UnknownHostException {
    registeringUser.setUsername("mystarrocks");
    return new ResponseEntity<RegisteringUser>(registeringUser, HttpStatus.OK);
  }
}