package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.EmployeeSTC;
import com.manjavacas.fence.repository.EmployeeSTCRepository;

@Service
public class EmployeeSTCService {

	@Autowired
	EmployeeSTCRepository employeeSTCRepository;

	public List<EmployeeSTC> getAllEmployeeSTC() {
		return employeeSTCRepository.findAll();
	}

	public List<EmployeeSTC> getEmployeeSTC(String dni) {
		return employeeSTCRepository.findByEmployee(dni);
	}

	public void addEmployeeSTC(EmployeeSTC employeeSTC) {
		employeeSTCRepository.insert(employeeSTC);
	}

}
