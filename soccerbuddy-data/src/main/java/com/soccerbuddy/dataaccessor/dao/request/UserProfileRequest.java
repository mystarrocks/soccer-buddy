package com.soccerbuddy.dataaccessor.dao.request;

import java.util.Set;

import org.bson.types.ObjectId;

public class UserProfileRequest {
	@MongoInfo (property="_id",filterType = FilterType.IN)
    Set<ObjectId> ids;
	@MongoInfo (property="name",filterType = FilterType.EQUALS)
	String name;
	
}
