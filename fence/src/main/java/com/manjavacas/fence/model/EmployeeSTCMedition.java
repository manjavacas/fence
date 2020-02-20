package com.manjavacas.fence.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dnis_stc_history")
public class EmployeeSTCMedition {

	@Id
	private ObjectId _id;

	private String dni;
	private Date date;
	private double stc;

	public EmployeeSTCMedition() {
	}

	public EmployeeSTCMedition(String dni, Date date, double stc) {
		super();
		this.dni = dni;
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
		return dni;
	}

	public void setEmployee(String dni) {
		this.dni = dni;
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
