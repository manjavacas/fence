package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees_stc_history")
public class EmployeeSTCMedition {

	private ObjectId _id;

	private String employee;
	private Date date;
	private double stc;

	public EmployeeSTCMedition() {
	}

	public EmployeeSTCMedition(String employee, Date date, double stc) {
		super();
		this.employee = employee;
		this.date = date;
		this.stc = stc;
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
