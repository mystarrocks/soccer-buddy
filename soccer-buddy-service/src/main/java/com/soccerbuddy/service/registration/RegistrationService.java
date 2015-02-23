package com.soccerbuddy.service.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RegistrationService {
  
  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<RegisteringUser> registerUser(@RequestBody RegisteringUser registeringUser) {
    registeringUser.setUsername("sunil");
    return new ResponseEntity<RegisteringUser>(registeringUser, HttpStatus.OK);
  }
}