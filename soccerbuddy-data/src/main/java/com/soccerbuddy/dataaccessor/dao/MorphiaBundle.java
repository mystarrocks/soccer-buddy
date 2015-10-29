package com.soccerbuddy.dataaccessor.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.mongodb.morphia.Morphia;

import com.soccerbuddy.dataaccessor.model.UserProfile;

public class MorphiaBundle {
	private final static Set<Class<?>> CLAZZES_TO_MAP = Collections.unmodifiableSet(new HashSet<Class<?>>(Arrays.asList(UserProfile.class)));
	private final static Morphia morphia = initializeMorphia();
	
	private static Morphia initializeMorphia(){
		Morphia morphia = new Morphia();
		System.out.println(CLAZZES_TO_MAP);
		for (Class<?> clazz : CLAZZES_TO_MAP) {
		  morphia.map(clazz);
		}
		return morphia;
	}
	
	/**
	 * Gets you the {@code Morphia} bundle to work with.
	 * 
	 * @return the {@code Morphia} bundle to work with
	 */
	public static final Morphia morphiaBundle(){
		return morphia;
	}
}
