package com.soccerbuddy.dataaccessor.dao.request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.mapping.MappingException;
import org.mongodb.morphia.utils.ReflectionUtils;

/**
 * This is a request descriptor that lists the {@Code MongoMappedFields} based on
 * {@link MongoInfo} for a find request
 * @author mchandramohan
 *
 */
public class MongoMappedRequest {
	private Class<?> actualClass;
	private List<MongoMappedFields> fieldDescriptors = new ArrayList<MongoMappedFields>();
	
	public List<MongoMappedFields> getFieldDescriptors() {
		return fieldDescriptors;
	}


	public MongoMappedRequest(Class actualClass){
		this.actualClass = actualClass;
		populateFieldDescriptors();
	}
	 
	
	private void populateFieldDescriptors(){
		 Field[] fields = ReflectionUtils.getDeclaredAndInheritedFields(actualClass, false);
		 for(Field field : fields){
			 field.setAccessible(true);
			 MongoInfo mongoInfo = field.getAnnotation(MongoInfo.class);
			 if(mongoInfo == null){
				 throw new MappingException("Mongo Information not specified for POJO to mongo conversion");
			 }
			 String property = mongoInfo.property();
			 if(property == null){
				 throw new MappingException("correspoding mongo property is not mapped for"+ field.getName());
			 }
			 FilterType filter = mongoInfo.filterType();
			 fieldDescriptors.add(new MongoMappedFields(field,property,filter));
		 }
	}
}
