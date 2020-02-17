package com.manjavacas.fence.model;

import java.sql.Timestamp;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
public class Admin {

	private ObjectId _id;

	private String username;
	private String email;
	private String password;
	private Timestamp create_time;

	public Admin() {
	}

	public Admin(String username, String email, String password, Timestamp create_time) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.create_time = create_time;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}
