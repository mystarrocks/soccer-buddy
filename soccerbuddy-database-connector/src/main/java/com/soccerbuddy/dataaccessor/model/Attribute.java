package com.soccerbuddy.dataaccessor.model;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Attribute {
	private int age;
	private char gender;
	private String favoriteFoot;
	private String postition;
	private String experience;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getFavoriteFoot() {
		return favoriteFoot;
	}
	public void setFavoriteFoot(String favoriteFoot) {
		this.favoriteFoot = favoriteFoot;
	}
	public String getPostition() {
		return postition;
	}
	public void setPostition(String postition) {
		this.postition = postition;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
}
