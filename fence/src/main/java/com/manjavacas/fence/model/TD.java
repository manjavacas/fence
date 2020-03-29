package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TD")
public class TD {

	@Id
	private ObjectId _id;

	private String task1;
	private String task2;
	private String project;
	private double weight;

	public TD() {
	}

	public TD(String task1, String task2, String project, double weight) {
		super();
		this.task1 = task1;
		this.task2 = task2;
		this.project = project;
		this.weight = weight;
	}

	public String getTask1() {
		return task1;
	}

	public void setTask1(String task1) {
		this.task1 = task1;
	}

	public String getTask2() {
		return task2;
	}

	public void setTask2(String task2) {
		this.task2 = task2;
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
