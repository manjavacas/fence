package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams_stc_history")
public class TeamSTC {

	@Id
	private ObjectId _id;

	private String team;
	private double stc;
	private Date date;

	public TeamSTC() {
	}

	public TeamSTC(String team, double stc, Date date) {
		super();
		this.team = team;
		this.stc = stc;
		this.date = date;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
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
