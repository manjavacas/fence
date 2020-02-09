package com.manjavacas.fence.user;

public class User {

	private int id, age;
	private String genre, role, timezone, country, experience;
	private String[] languages;
	private double stc;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, int age, String genre, String role, String timezone, String country, String experience,
			String[] languages, double stc) {
		super();
		this.id = id;
		this.age = age;
		this.genre = genre;
		this.role = role;
		this.timezone = timezone;
		this.country = country;
		this.experience = experience;
		this.languages = languages;
		this.stc = stc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String[] getLanguages() {
		return languages;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public double getStc() {
		return stc;
	}

	public void setStc(double stc) {
		this.stc = stc;
	}

}
