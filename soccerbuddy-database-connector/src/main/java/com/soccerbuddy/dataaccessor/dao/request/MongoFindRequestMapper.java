package com.soccerbuddy.dataaccessor.dao.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mongodb.morphia.query.Query;

public class MongoFindRequestMapper {
	
	private final static Map<String, MongoMappedRequest> requestMap = initializeMappedRequests();
	
	private static final Map<String, MongoMappedRequest> initializeMappedRequests(){
		Map<String, MongoMappedRequest> requests = new ConcurrentHashMap<String, MongoMappedRequest>();
		requests.put("UserProfileRequest",new MongoMappedRequest(UserProfileRequest.class));
		return requests;
	}
	
	private static MongoMappedRequest getRequest(String className){
		return requestMap.get(className);
	}
	
	public static <T> Query<T> buildFindRequest(Query<T> q, Object request) {
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
