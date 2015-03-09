package com.soccerbuddy.dataaccessor.dao.request;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * This pojo is to hold the configuration of {@link MongoInfo} used in the find requests
 * which will be consumed in {@code MongoFindRequestMapper}}
 * @author mchandramohan
 *
 */
@Data
@AllArgsConstructor
public class MongoMappedFields {
	Field field;
	String property;
	FilterType filterType;
}
