package com.manjavacas.fence.model;

/**
 * 
 * Task assignment
 *
 */
public class TA {

	private String user;
	private String task;
	private String project;
	private double weight;

	public TA() {

	}

	public TA(String user, String task, String project, double weight) {
		super();
		this.user = user;
		this.task = task;
		this.project = project;
		this.weight = weight;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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
