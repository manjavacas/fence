package com.manjavacas.fence.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {

	@Id
	private ObjectId _id;

	private String reference;
	private String description;
	private double duration_days;
	private String priority;
	private boolean done;
	private String project;
	private String type;
	private List<String> assigned_to;

	private double priorityNum;

	public Task() {
	}

	public Task(String reference, String description, double duration_days, String priority, boolean done,
			String project, String type, List<String> assigned_to) {
		super();
		this.reference = reference;
		this.description = description;
		this.duration_days = duration_days;
		this.priority = priority;
		this.done = done;
		this.project = project;
		this.type = type;
		this.assigned_to = assigned_to;
	}

	public Task(String reference, String description, double duration_days, String priority, boolean done,
			String project, String type) {
		super();
		this.reference = reference;
		this.description = description;
		this.duration_days = duration_days;
		this.priority = priority;
		this.done = done;
		this.project = project;
		this.type = type;
		this.assigned_to = new ArrayList<String>();
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDuration_days() {
		return duration_days;
	}

	public void setDuration_days(double duration_days) {
		this.duration_days = duration_days;
	}

	public String getPriority() {
		return priority;
	}

	public double getPriorityNum() {
		switch (this.priority) {
		case "HIGH":
			this.priorityNum = 2;
			break;
		case "NORMAL":
			this.priorityNum = 1.5;
			break;
		case "LOW":
			this.priorityNum = 1;
			break;
		default:
			this.priorityNum = 1;
		}

		return this.priorityNum;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getAssigned_to() {
		return assigned_to;
	}

	public void setAssigned_to(List<String> assigned_to) {
		this.assigned_to = assigned_to;
	}

}