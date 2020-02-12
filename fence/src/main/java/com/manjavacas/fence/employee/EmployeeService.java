package com.manjavacas.fence.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public ArrayList<Employee> getAllEmployees() {
		return null;
	}

	public Employee getEmployee(String id) {
		return employeeRepository.findBy_Id(id);
	}

	public void addEmployee(Employee e) {

	}

	public void updateEmployee(String id, Employee e) {

	}

	public void deleteEmployee(String id) {

	}

}