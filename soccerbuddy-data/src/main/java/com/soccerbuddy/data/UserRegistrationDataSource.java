package com.soccerbuddy.data;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.Optional;

import com.googlecode.objectify.Key;
import com.soccerbuddy.model.RegisteringUser;

/**
 * The data source that helps the application persist/query
 * user registration related information.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see DataSources
 */
class UserRegistrationDataSource implements DataSource<RegisteringUser, String> {
  
  /**
   * Returns a new instance of this data source.
   * 
   * @return a new instance of this data source
   */
  static UserRegistrationDataSource newInstance() {
    return new UserRegistrationDataSource();
  }
  
  @Override
  public RegisteringUser insertNew(RegisteringUser user) {
    ofy()
        .save()
        .entity(user)
        .now();
    return user;
  }
  
  @Override
  public RegisteringUser updateExisting(RegisteringUser user) {
    ofy()
        .save()
        .entity(user)
        .now();
    return user;
  }

  @Override
  public void deleteExisting(RegisteringUser e) {
    throw new UnsupportedOperationException("Deletion of [registered] users is not allowed;"
        + " If you want to unsubscribe the user, update the registration status of the user.");
  }
  
  @Override
  public Optional<RegisteringUser> findOne(String key) {
    return Optional
        .ofNullable(ofy()
            .load()
            .type(RegisteringUser.class)
            .id(key)
            .now());
  }
  
  @Override
  public Optional<RegisteringUser> findOne(Key<String> key) {
    return null;
  }
}
