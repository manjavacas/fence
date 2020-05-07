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
	private String project;

	public Recommendation(String user1, String user2, String text, String project) {
		this.user1 = user1;
		this.user2 = user2;
		this.text = text;
		this.project = project;
	}

	public Recommendation(String user, String text, String project) {
		this.user1 = user;
		this.text = text;
		this.project = project;
	}

	public Recommendation() {

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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Override
	public String toString() {
		if (user2 == null) {
			return "Recommendation for " + user1 + ": " + text;
		} else {
			return "Recommendation for " + user1 + " and " + user2 + ": " + text;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user1 == null) ? 0 : user1.hashCode());
		result = prime * result + ((user2 == null) ? 0 : user2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass()) {
			return false;
		}
		Recommendation other = (Recommendation) obj;
		return (this.user1.equals(other.user1) && this.user2.equals(other.user2) && this.text.equals(other.text))
				|| (this.user2.equals(other.user1) && this.user1.equals(other.user2) && this.text.equals(other.text));

	}

}
