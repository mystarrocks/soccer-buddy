package com.soccerbuddy.dataaccessor.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.WriteResult;
import com.soccerbuddy.dataaccessor.dao.request.UserProfileRequest;
import com.soccerbuddy.dataaccessor.model.UserProfile;

/**
 * FIXME: add documenation.
 *
 */
public class UserProfileDao extends BasicDAO<UserProfile, ObjectId> implements BaseDao<UserProfile,UserProfileRequest>{

	public UserProfileDao(Class<UserProfile> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

	@Override
	public UserProfile find(UserProfileRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile findAll(UserProfileRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Key<UserProfile>> createList(List<UserProfile> entities) {
		List<Key<UserProfile>> keyList = new ArrayList<Key<UserProfile>>();
		for(Key<UserProfile> k:ds.save(entities)){
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
