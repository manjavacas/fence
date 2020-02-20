package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project_stc_history")
public class ProjectSTCMedition {

	@Id
	private ObjectId _id;

	private String project;
	private Date date;
	private double stc;

	public ProjectSTCMedition() {
	}

	public ProjectSTCMedition(String project, Date date, double stc) {
		super();
		this.project = project;
		this.date = date;
		this.stc = stc;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getStc() {
		return stc;
	}

	public void setStc(double stc) {
		this.stc = stc;
	}

}
