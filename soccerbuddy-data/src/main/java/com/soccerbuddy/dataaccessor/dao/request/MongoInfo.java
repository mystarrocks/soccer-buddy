package com.soccerbuddy.dataaccessor.dao.request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This field annotation is to mark the property and filter Type used in find requests which
 * gets translated at {@code MongoMappedRequest}}
 * @author mchandramohan
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) 
public @interface MongoInfo {
	String property();
	FilterType filterType() default FilterType.EQUALS;
}
