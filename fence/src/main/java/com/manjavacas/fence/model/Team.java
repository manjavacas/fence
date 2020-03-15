package com.manjavacas.fence.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
public class Team {

	@Id
	private ObjectId _id;

	private String name;
	private String location;
	private String project;
	private List<String> employees;

	public Team() {
	}

	public Team(String name, String location, String project, List<String> employees) {
		super();
		this.name = name;
		this.location = location;
		this.project = project;
		this.employees = employees;
	}
	
	public Team(String name, String location, String project) {
		super();
		this.name = name;
		this.location = location;
		this.project = project;
		this.employees = new ArrayList<String>();
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public List<String> getEmployees() {
		return employees;
	}

	public void setEmployees(List<String> employees) {
		this.employees = employees;
	}

}
