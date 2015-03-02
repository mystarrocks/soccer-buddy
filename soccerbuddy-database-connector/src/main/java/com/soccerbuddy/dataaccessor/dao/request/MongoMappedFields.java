package com.soccerbuddy.dataaccessor.dao.request;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MongoMappedFields {
	Field field;
	String property;
	FilterType filterType;
}
