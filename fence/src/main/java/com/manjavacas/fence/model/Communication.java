package com.manjavacas.fence.model;

import java.sql.Timestamp;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "communications")
public class Communication {

	private ObjectId _id;

	private String starter;
	private String listener;
	private String project;
	private Timestamp time_start;
	private Timestamp time_end;
	private String quality;

	public Communication() {
	}

	public Communication(String starter, String listener, String project, Timestamp time_start,
			Timestamp time_end, String quality) {
		super();
		this.starter = starter;
		this.listener = listener;
		this.project = project;
		this.time_start = time_start;
		this.time_end = time_end;
		this.quality = quality;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public String getListener() {
		return listener;
	}

	public void setListener(String listener) {
		this.listener = listener;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Timestamp getTime_start() {
		return time_start;
	}

	public void setTime_start(Timestamp time_start) {
		this.time_start = time_start;
	}

	public Timestamp getTime_end() {
		return time_end;
	}

	public void setTime_end(Timestamp time_end) {
		this.time_end = time_end;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

}
