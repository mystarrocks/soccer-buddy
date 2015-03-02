package com.soccerbuddy.dataaccessor.dao.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mongodb.morphia.query.Query;

public class MongoFindRequestMapper {
	
	private final Map<String, MongoMappedRequest> requestMap = new ConcurrentHashMap<String, MongoMappedRequest>();
	
	private MongoMappedRequest getRequest(String className){
		return requestMap.get(className);
	}
	
	public <T> Query<T> buildFindRequest(Query<T> q, Object request) {
		MongoMappedRequest mappedRequest = getRequest(request.getClass().getName());

		for (MongoMappedFields mappedField : mappedRequest.getFieldDescriptors()) {
			Object value = null;
			try {
				value = mappedField.getField().get(request);
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			// ignore value is null
			if (value != null ) {
				FilterType operation = mappedField.getFilterType();
				q.filter(mappedField.getProperty()+" "+operation.getValue(),value) ;
			}
			
		}
		return q;
	}

}
