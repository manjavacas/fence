package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task_assignments")
public class TaskAssignment {

	@Id
	private ObjectId _id;

	private String user;
	private String task;
	private String project;

	public TaskAssignment() {
	}

	public TaskAssignment(String task, String user, String project) {
		super();
		this.task = task;
		this.user = user;
		this.project = project;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

}
