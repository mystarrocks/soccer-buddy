package com.soccerbuddy.dataaccessor.dao.request;

public enum FilterType {
	IN("in"),
	NIN("nin"),
	EQUALS("="),
	GREATER_THAN(">"),
	GREATER_THAN_EQUAL(">="),
	LESSER_THAN("<"),
	LESSER_THAN_EQUAL("<="),
	NOTEQUALS("<>"),
	ELEMENT_MATCH("elem"),
	SIZE("size"),
	WITHIN("within"),
	NEAR("near"),
	ALL("all"),
	EXISTS("exists");
	
	private String value;
	
    FilterType(String value){
		this.value = value;
	}
}
