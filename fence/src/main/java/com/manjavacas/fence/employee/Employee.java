package com.manjavacas.fence.employee;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

	private ObjectId _id;

	private String name;
	private String email;
	private String genre;
	private int age;
	private String role;
	private String timezone;
	private String country;
	private String experience;
	private double stc;

	public Employee() {
	}

	public Employee(ObjectId _id, String name, String email, String genre, int age, String role, String timezone,
			String country, String experience, double stc) {
		super();
		this._id = _id;
		this.name = name;
		this.email = email;
		this.genre = genre;
		this.age = age;
		this.role = role;
		this.timezone = timezone;
		this.country = country;
		this.experience = experience;
		this.stc = stc;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public double getStc() {
		return stc;
	}

	public void setStc(double stc) {
		this.stc = stc;
	}

}