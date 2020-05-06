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
	private String value;
	private String project;

	public TaskDependency() {
	}

	public TaskDependency(String task1, String task2, String value, String project) {
		super();
		this.task1 = task1;
		this.task2 = task2;
		this.value = value;
		this.project = project;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public double getValueWeight() {

		double valueWeight = 0;

		switch (this.value) {
		case "VERY HIGH":
			valueWeight = 1;
			break;
		case "HIGH":
			valueWeight = .8;
			break;
		case "MEDIUM":
			valueWeight = .6;
			break;
		case "LOW":
			valueWeight = .4;
			break;
		default: // "VERY LOW"
			valueWeight = .2;
		}

		return valueWeight;
	}

}
