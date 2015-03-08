package com.soccerbuddy.dataaccessor.dao;

import static mockit.Deencapsulation.setField;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.DatastoreImpl;

import com.soccerbuddy.dataaccessor.model.UserProfile;

/**
 * Tests the {@link UserProfileDAO}'s public API.
 * 
 * @author mystarrocks
 * @since 1.0
 */
@RunWith (JMockit.class)
public class UserProfileDAOTest {
  
  @Mocked private DatastoreImpl mockDatastore;
  
  /**
   * Tests the {@link UserProfileDAO#getInstance()} method for successful execution.
   * 
   * <p>
   * It is known that this method will never throw any exception and therefore won't fail.
   */
  @Test
  public void getInstance_Success() {
    new MockUp<AbstractDAO<?, ?>>() {
      @Mock public void $clinit() {
        setField(AbstractDAO.class, "SOCCERBUDDY_DATASTORE", mockDatastore); 
      }
    };
    UserProfileDAO instance = UserProfileDAO.getInstance();
    
    assertThat(instance.getEntityClazz(), is(equalTo(UserProfile.class)));
    assertThat(instance.getDs(), is(mockDatastore));
  }
}
