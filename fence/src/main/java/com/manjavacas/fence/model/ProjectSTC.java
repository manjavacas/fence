package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects_stc_history")
public class ProjectSTC {

	@Id
	private ObjectId _id;

	private String project;
	private double stc;
	private Date date;

	public ProjectSTC() {
	}

	public ProjectSTC(String project, double stc, Date date) {
		super();
		this.project = project;
		this.stc = stc;
		this.date = date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public double getStc() {
		return stc;
	}

	public void setStc(double stc) {
		this.stc = stc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
