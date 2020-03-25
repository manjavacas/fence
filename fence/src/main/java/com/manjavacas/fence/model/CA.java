package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * Actual coordination
 *
 */

@Document(collection = "CA")
public class CA {

	@Id
	private ObjectId _id;

	private String user1;
	private String user2;
	private String task;
	private String project;
	private double weight;

	public CA() {

	}

	public CA(String user1, String user2, String task, String project, double weight) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.task = task;
		this.project = project;
		this.weight = weight;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
