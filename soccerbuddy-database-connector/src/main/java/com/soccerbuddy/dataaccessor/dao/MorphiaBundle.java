package com.soccerbuddy.dataaccessor.dao;

import org.mongodb.morphia.Morphia;

import com.soccerbuddy.dataaccessor.model.UserProfile;

public class MorphiaBundle {
	private final static Morphia morphia = initializeMorphiaInstance();
	
	private static Morphia initializeMorphiaInstance(){
		Morphia m = new Morphia();
		m.map(UserProfile.class);
		return m;
	}
	
	public static Morphia getMorphiaBundle(){
		return morphia;
	}
}
