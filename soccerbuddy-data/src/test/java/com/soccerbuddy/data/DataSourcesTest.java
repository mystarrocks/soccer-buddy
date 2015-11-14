package com.soccerbuddy.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soccerbuddy.model.RegisteringUser;
import com.soccerbuddy.model.UserRegistrationStatus;

/**
 * Tests the {@link DataSources}'s public API.
 * 
 * @author mystarrocks
 */
@RunWith (PowerMockRunner.class)
@PrepareForTest (value = {UserRegistrationDataSource.class, DataSources.class})
public class DataSourcesTest {
  
  private static final RegisteringUser INSERTED_USER = mock(RegisteringUser.class, "insertedUser");
  
  /**
   * Tests the {@link DataSources#registerUser(com.soccerbuddy.model.RegisteringUser)}
   * for a brand new user that does not already exist in the data store.
   * 
   * @throws Exception if the mocks in test threw one
   */
  @Test
  public void registerUser_NonExistingNewUser() throws Exception {
    RegisteringUser newUser = mock(RegisteringUser.class, "newUser");
    when(newUser.emailId()).thenReturn("sunilceg7@gmail.com");
    
    UserRegistrationDataSource ds = mock(UserRegistrationDataSource.class, "dataSource");
    when(ds.insertNew(any(RegisteringUser.class))).thenReturn(INSERTED_USER);
    
    spy(DataSources.class);
    doReturn(ds).when(DataSources.class, "registrationDataSource");
    when(ds.findOne("sunilceg7@gmail.com")).thenReturn(Optional.ofNullable(null));
    doCallRealMethod().when(DataSources.class, "registerUser", newUser);
    
    RegisteringUser result = DataSources.registerUser(newUser);
    
    assertThat(result, is(INSERTED_USER));
  }
  
  /**
   * Tests the {@link DataSources#registerUser(com.soccerbuddy.model.RegisteringUser)}
   * for a user that does already exist in the data store in an active state.
   * 
   * @throws Exception if the mocks in test threw one
   */
  @Test
  public void registerUser_ExistingUser_AlreadyActive() throws Exception {
    RegisteringUser newUser = mock(RegisteringUser.class, "newUser");
    RegisteringUser savedUser = mock(RegisteringUser.class, "savedUser");
    when(newUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.status()).thenReturn(UserRegistrationStatus.ACTIVE);
    when(savedUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.userName()).thenReturn("mystarrocks");
    
    UserRegistrationDataSource ds = mock(UserRegistrationDataSource.class, "dataSource");
    
    spy(DataSources.class);
    doReturn(ds).when(DataSources.class, "registrationDataSource");
    when(ds.findOne("sunilceg7@gmail.com")).thenReturn(Optional.of(savedUser));
    doCallRealMethod().when(DataSources.class, "registerUser", newUser);
    
    try {
      DataSources.registerUser(newUser);
      fail("Expected a DataStoreException to be thrown");
    } catch (DataStoreException e) {
      List<Pair<String, Object>> context = e.getContextEntries();
      assertThat(context.get(0), is(Pair.of("Username", "mystarrocks")));
      assertThat(context.get(1), is(Pair.of("Email id", "sunilceg7@gmail.com")));
    }
  }
  
  /**
   * Tests the {@link DataSources#registerUser(com.soccerbuddy.model.RegisteringUser)}
   * for a user that does already exist in the data store in an unknown state.
   * 
   * @throws Exception if the mocks in test threw one
   */
  @Test
  public void registerUser_ExistingUser_UnknownStatus() throws Exception {
    RegisteringUser newUser = mock(RegisteringUser.class, "newUser");
    RegisteringUser savedUser = mock(RegisteringUser.class, "savedUser");
    when(newUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.status()).thenReturn(null); // null statuses are defaulted to UNKNOWN
    when(savedUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.userName()).thenReturn("mystarrocks");
    
    UserRegistrationDataSource ds = mock(UserRegistrationDataSource.class, "dataSource");
    when(ds.insertNew(any(RegisteringUser.class))).thenReturn(INSERTED_USER);
    
    spy(DataSources.class);
    when(ds.findOne("sunilceg7@gmail.com")).thenReturn(Optional.of(savedUser));
    doCallRealMethod().when(DataSources.class, "registerUser", newUser);
    doReturn(ds).when(DataSources.class, "registrationDataSource");
    
    RegisteringUser result = DataSources.registerUser(newUser);
    
    assertThat(result, is(INSERTED_USER));
  }
  
  /**
   * Tests the {@link DataSources#registerUser(com.soccerbuddy.model.RegisteringUser)}
   * for a user that does already exist in the data store in an inactive state.
   * 
   * @throws Exception if the mocks in test threw one
   */
  @Test
  public void registerUser_ExistingUser_Inactive() throws Exception {
    RegisteringUser newUser = mock(RegisteringUser.class, "newUser");
    RegisteringUser savedUser = mock(RegisteringUser.class, "savedUser");
    when(newUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.status()).thenReturn(UserRegistrationStatus.INACTIVE);
    when(savedUser.emailId()).thenReturn("sunilceg7@gmail.com");
    when(savedUser.userName()).thenReturn("mystarrocks");
    
    UserRegistrationDataSource ds = mock(UserRegistrationDataSource.class, "dataSource");
    when(ds.insertNew(any(RegisteringUser.class))).thenReturn(INSERTED_USER);
    
    spy(DataSources.class);
    when(ds.findOne("sunilceg7@gmail.com")).thenReturn(Optional.of(savedUser));
    doCallRealMethod().when(DataSources.class, "registerUser", newUser);
    doReturn(ds).when(DataSources.class, "registrationDataSource");
    
    RegisteringUser result = DataSources.registerUser(newUser);
    
    assertThat(result, is(INSERTED_USER));
  }
}
