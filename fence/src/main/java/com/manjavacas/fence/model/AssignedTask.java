package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assigned_tasks")
public class AssignedTask {

	@Id
	private ObjectId _id;

	private int task;
	private String employee;

	public AssignedTask() {
	}

	public AssignedTask(int task, String employee) {
		super();
		this.task = task;
		this.employee = employee;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public int getTask() {
		return task;
	}

	public void setTask(int task) {
		this.task = task;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

}
