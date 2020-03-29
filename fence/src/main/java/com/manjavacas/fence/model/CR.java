package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CR")
public class CR {

	@Id
	private ObjectId _id;

	private String user1;
	private String user2;
	private String project;
	private String task;
	private double weight;

	public CR() {
	}

	public CR(String user1, String user2, String project, String task, double weight) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.project = project;
		this.task = task;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
