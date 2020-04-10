package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

	@Id
	private ObjectId _id;

	private String dni;
	private String name;
	private String email;
	private String genre;
	private int age;
	private String role;
	private String timezone;
	private String country;
	private String experience;
	private String team;

	private double experienceNum;

	public Employee() {
	}

	public Employee(String dni, String name, String email, String genre, int age, String role, String timezone,
			String country, String experience, String team) {
		super();
		this.dni = dni;
		this.name = name;
		this.email = email;
		this.genre = genre;
		this.age = age;
		this.role = role;
		this.timezone = timezone;
		this.country = country;
		this.experience = experience;
		this.team = team;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public double getExperienceNum() {
		switch (this.experience) {
		case "VERY HIGH":
			this.experienceNum = 1;
			break;
		case "HIGH":
			this.experienceNum = .75;
			break;
		case "MEDIUM":
			this.experienceNum = .5;
			break;
		case "LOW":
			this.experienceNum = .25;
			break;
		default: // "VERY LOW"
			this.experienceNum = 0;
		}

		return this.experienceNum;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

}