package com.soccerbuddy.dataaccessor.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.WriteResult;
import com.soccerbuddy.dataaccessor.dao.request.MongoFindRequestMapper;
import com.soccerbuddy.dataaccessor.dao.request.UserProfileRequest;
import com.soccerbuddy.dataaccessor.model.UserProfile;

/**
 * The data accessor type associated with the {@link UserProfile} model object.
 * 
 * @author manob2w
 * @author mystarrocks
 * @since 1.0
 */
public class UserProfileDAO extends AbstractDAO<UserProfile, ObjectId> implements BaseDAO<UserProfile, UserProfileRequest> {
  private static final Datastore SOCCERBUDDY_DATASTORE = dataStore();
  private static final UserProfileDAO INSTANCE = new UserProfileDAO(SOCCERBUDDY_DATASTORE);

  /**
   * Gets you an instance (the sole) of this type: {@code UserProfileDao} to work with.
   * 
   * @return an instance (the sole) of this type: {@code UserProfileDao} to work with
   */
  public static UserProfileDAO getInstance() {
    return INSTANCE;
  }

  /**
   * Instantiates this type given the {@code Mongo data store} this instance is going to use to perform database operations.
   * 
   * @param datastore  the {@code Mongo data store} object this instance is going to use to perform database operations
   */
  private UserProfileDAO(Datastore datastore) {
    super(UserProfile.class, datastore);
  }

  @Override
  public UserProfile find(UserProfileRequest request) {
    Query<UserProfile> query = MongoFindRequestMapper.buildFindRequest(
        super.createQuery(), request);
    return query.get();
  }

  @Override
  public List<UserProfile> findAll(UserProfileRequest request) {
    Query<UserProfile> query = MongoFindRequestMapper.buildFindRequest(
        super.createQuery(), request);
    return query.asList();
  }

  @Override
  public List<Key<UserProfile>> createList(List<UserProfile> entities) {
    List<Key<UserProfile>> keyList = new ArrayList<Key<UserProfile>>();
    for (Key<UserProfile> k : getDs().save(entities)) {
      keyList.add(k);
    }
    return keyList;

  }

  @Override
  public UpdateResults update(UserProfileRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public WriteResult deleteAllByMatch(UserProfileRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}
