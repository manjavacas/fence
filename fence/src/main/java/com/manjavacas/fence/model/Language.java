package com.manjavacas.fence.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "languages")
public class Language {

	private ObjectId _id;

	private String language;
	private String employee;

	public Language() {
	}

	public Language(String language, String employee) {
		super();
		this.language = language;
		this.employee = employee;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

}
