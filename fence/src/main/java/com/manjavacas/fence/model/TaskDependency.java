package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task_dependencies")
public class TaskDependency {

	@Id
	private ObjectId _id;

	private String task1;
	private String task2;
	private String project;
	private String value;

	public TaskDependency() {
	}

	public TaskDependency(String task1, String task2, String project, String value) {
		super();
		this.task1 = task1;
		this.task2 = task2;
		this.project = project;
		this.value = value;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	public String getValueTag() {
		return value;
	}

	public void setValueTag(String value) {
		this.value = value;
	}

	public double getValue() {
		switch (this.value) {
		case "VERY HIGH":
			return 1;
		case "HIGH":
			return 0.75;
		case "MEDIUM":
			return 0.5;
		case "LOW":
			return 0.25;
		default: // VERY LOW
			return 0;
		}
	}

}
