package com.soccerbuddy.dal.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import com.soccerbuddy.dal.model.UserProfile;

public class UserProfileDao extends BasicDAO<UserProfile, ObjectId> {

	public UserProfileDao(Class<UserProfile> entityClass, Datastore ds) {
		super(entityClass, ds);
	}

}
