package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recommendations")
public class Recommendation {

	@Id
	private ObjectId _id;
	
	private String user1;
	private String user2;
	private String text;

	public Recommendation(String user1, String user2, String text) {
		this.user1 = user1;
		this.user2 = user2;
		this.text = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Recommendation for " + user1 + " and " + user2 + ": " + text;
	}

}
