package com.soccerbuddy.service.registration;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.soccerbuddy.dataaccessor.dao.UserProfileDao;
import com.soccerbuddy.dataaccessor.model.UserProfile;

/**
 * The webservice controller that handles user registration related activities.
 * 
 * @author mystarrocks
 */
@RestController
class RegistrationService {
  
  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
  public @ResponseBody ResponseEntity<RegisteringUser> registerUser(@RequestBody RegisteringUser registeringUser) throws UnknownHostException {
    registeringUser.setUsername("sunil");
    MongoClientURI uri = new MongoClientURI("mongodb://soccer-buddy:soccer@ds052827.mongolab.com:52827/soccer-buddy");
    MongoClient m = new MongoClient(uri);
    Morphia morphia = new Morphia();
    morphia.map(UserProfile.class);
    Datastore ds = morphia.createDatastore(m, "soccer-buddy");
    
        UserProfile us = new UserProfile();
        us.setEmail(registeringUser.getEmailId());
        us.setPassword(registeringUser.getPassword());
        us.setName(registeringUser.getUsername());
        UserProfileDao userDao = new UserProfileDao(UserProfile.class, ds);    
        userDao.
        userDao.save(us);
    return new ResponseEntity<RegisteringUser>(registeringUser, HttpStatus.OK);
  }
}