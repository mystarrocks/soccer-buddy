package com.soccerbuddy.dataaccessor.dao.request;

import java.util.ArrayList;
import java.util.List;

public class MongoMappedRequest {
	private Class<?> clazz;
	List<MongoMappedFields> fieldDescriptors = new ArrayList<MongoMappedFields>();
	
	public MongoMappedRequest(Class clazz){
		this.clazz = clazz;
		populateFieldDescriptors();
	}
	
	private void populateFieldDescriptors(){
		
	}
}
