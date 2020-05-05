package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "min_gaps")
public class MinGap {

	@Id
	private ObjectId _id;

	private String user1;
	private String user2;
	private String project;
	private double customWeight;

	public MinGap() {
	}

	public MinGap(String user1, String user2, String project, double customWeight) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.project = project;
		this.customWeight = customWeight;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	public double getCustomWeight() {
		return customWeight;
	}

	public void setCustomWeight(double customWeight) {
		this.customWeight = customWeight;
	}

}
