package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees_stc_history")
public class EmployeeSTC {

	@Id
	private ObjectId _id;

	private String employee;
	private double stc;
	private Date date;

	public EmployeeSTC() {
	}

	public EmployeeSTC(String employee, double stc, Date date) {
		super();
		this.employee = employee;
		this.stc = stc;
		this.date = date;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
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
