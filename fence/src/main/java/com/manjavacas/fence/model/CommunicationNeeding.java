package com.manjavacas.fence.model;

public class CommunicationNeeding {

	private Employee employee;
	private double lackOfCoordination;

	public CommunicationNeeding() {
	}

	public CommunicationNeeding(Employee employee, double lackOfCoordination) {
		super();
		this.employee = employee;
		this.lackOfCoordination = lackOfCoordination;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getLackOfCoordination() {
		return lackOfCoordination;
	}

	public void setLackOfCoordination(double lackOfCoordination) {
		this.lackOfCoordination = lackOfCoordination;
	}

}
