package com.soccerbuddy.dataaccessor.dao.request;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class MongoMappedFields {
	String property;
	FilterType filterType;
}
