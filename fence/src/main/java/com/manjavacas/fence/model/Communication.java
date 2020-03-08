package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "communications")
public class Communication {

	@Id
	private ObjectId _id;

	private String user1;
	private String user2;
	private String duration;
	private Date date;
	private String quality;

	public Communication() {
	}

	public Communication(ObjectId _id, String user1, String user2, String duration, Date date, String quality) {
		super();
		this._id = _id;
		this.user1 = user1;
		this.user2 = user2;
		this.duration = duration;
		this.date = date;
		this.quality = quality;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration (String duration) {
		this.duration = duration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

}
