package com.soccerbuddy.data;

import java.util.Optional;

import com.google.api.services.datastore.DatastoreV1.Entity;
import com.soccerbuddy.model.RegisteringUser;
import com.soccerbuddy.model.UserRegistrationStatus;

/**
 * The static utility method twin of the {@code DataSource}.
 * 
 * <p>
 * This effectively final class acts as a factory of data source instances that help
 * the client obtain the right data source implementation based on
 * the model object type it deals with.
 * 
 * <p>
 * At this time, the only support entity object type is {@link Entity},
 * the default entity type for all the model object types.
 * 
 * @author mystarrocks
 * @since 1.0
 * @see DataSource
 */
public class DataSources {
  
  private static final DataSource<RegisteringUser, String> USER_REGISTRATION_DATA_SOURCE = UserRegistrationDataSource.newInstance();
  
  /**
   * Prevent subclassing.
   */
  private DataSources() {}
  
  /**
   * Registers the given user by inserting/updating a new/existing entity into/in the data store.
   * 
   * <p>
   * This checks for presence of the user in the data store prior to inserting a new one.
   * If the search does find a user (with the same email as the given), an attempt to update
   * will be made to re-register depending on the current status of the user entity in the
   * data store, so this method doesn't always involve inserts.
   * 
   * <p>
   * This overwrites any status value set for the registering user to correspond
   * to that of a successful registration, so it's probably best left unset by the client.
   * 
   * @param user  the user to be registered
   * @return the registered user entity
   * @throws DataStoreException if a user with the given email already exists in the data store
   * that cannot be re-registered due to the current status
   */
  public static RegisteringUser registerUser(RegisteringUser user) throws DataStoreException {
    RegisteringUser savedUser = null;
    String emailId = user.emailId();
    
    Optional<RegisteringUser> dsUser = registrationDataSource().findOne(emailId);
    
    if (dsUser.isPresent()) {
      savedUser = handleRegistrationForAnExistingUser(dsUser.get());
    } else {
      user.status(UserRegistrationStatus.ACTIVE);
      savedUser = registrationDataSource().insertNew(user);
    }
    
    return savedUser;
  }

  private static RegisteringUser handleRegistrationForAnExistingUser(RegisteringUser dsUser) throws DataStoreException {
    RegisteringUser savedUser = null;
    
    UserRegistrationStatus status = Optional
        .ofNullable(dsUser.status())
        .orElse(UserRegistrationStatus.UNKNOWN);
    switch (status) {
    case ACTIVE:
    case ACTIVE_AGAIN:
      throw new DataStoreException("The user is already registered and active.")
          .addContextValue("Username", dsUser.userName())
          .addContextValue("Email id", dsUser.emailId());
    case INACTIVE:
    case INACTIVE_AGAIN:
    case UNKNOWN: /* Unlikely that such a status would ever exist in the data store */
      dsUser.status(UserRegistrationStatus.ACTIVE_AGAIN);
      savedUser = registrationDataSource().insertNew(dsUser);
      break;
    }
    return savedUser;
  }
  
  /**
   * Unregisters the given user by updating the existing entity in the data store.
   * 
   * <p>
   * This checks for presence of the user in the data store prior to updating it.
   * If the search does find a user (with the same email as the given), an attempt to update
   * will be made to un-register depending on the current status of the user entity in the
   * data store, so this method doesn't always involve updates. If, however, a user entity
   * was not found in the data store (with the given email id), an exception will be thrown
   * appropriately.
   * 
   * <p>
   * This overwrites any status value set for the unregistering user to correspond
   * to that of a successful unregistration, so it's probably best left unset by the client.
   * 
   * @param user  the user to be unregistered
   * @return the unregistered user entity
   * @throws DataStoreException if a user with the given email does not exist in the data store
   * or if it exists and cannot be unregistered [again, say]
   */
  public static RegisteringUser unregisterUser(RegisteringUser user) throws DataStoreException {
    RegisteringUser savedUser = null;
    String emailId = user.emailId();
    
    Optional<RegisteringUser> dsUser = registrationDataSource().findOne(emailId);
    
    if (dsUser.isPresent()) {
      savedUser = handleUnregistrationForAnExistingUser(dsUser.get());
    } else {
      throw new DataStoreException("No registered user found with the given details.")
          .addContextValue("Username", user.userName())
          .addContextValue("Email id", user.emailId());
    }
    
    return savedUser;
  }
  
  private static RegisteringUser handleUnregistrationForAnExistingUser(RegisteringUser dsUser) throws DataStoreException {
    RegisteringUser savedUser = null;
    
    UserRegistrationStatus status = Optional
        .ofNullable(dsUser.status())
        .orElse(UserRegistrationStatus.UNKNOWN);
    switch (status) {
    case ACTIVE:
      dsUser.status(UserRegistrationStatus.INACTIVE);
      savedUser = registrationDataSource().updateExisting(dsUser);
      break;
    case ACTIVE_AGAIN:
      dsUser.status(UserRegistrationStatus.INACTIVE_AGAIN);
      savedUser = registrationDataSource().updateExisting(dsUser);
      break;
    case UNKNOWN: /* Unlikely that such a status would ever exist in the data store */
      dsUser.status(UserRegistrationStatus.INACTIVE);
      savedUser = registrationDataSource().updateExisting(dsUser);
      break;
    case INACTIVE:
    case INACTIVE_AGAIN:
      throw new DataStoreException("No registered user found with the given details.")
          .addContextValue("Username", dsUser.userName())
          .addContextValue("Email id", dsUser.emailId());
    }
    return savedUser;
  }

  private static DataSource<RegisteringUser, String> registrationDataSource() {
    return USER_REGISTRATION_DATA_SOURCE;
  }
}
