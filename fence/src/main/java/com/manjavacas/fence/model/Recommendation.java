package com.manjavacas.fence.model;

public class Recommendation {

	private String user;
	private String text;

	public Recommendation(String user, String text) {
		this.user = user;
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Recommendation for " + user + ": " + text;
	}

}
