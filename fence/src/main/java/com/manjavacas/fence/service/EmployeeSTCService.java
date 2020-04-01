package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.EmployeeSTC;
import com.manjavacas.fence.repository.EmployeeSTCRepository;

@Service
public class EmployeeSTCService {

	@Autowired
	private EmployeeSTCRepository employeeSTCRepository;

	public List<EmployeeSTC> getAllEmployeeSTC() {
		return employeeSTCRepository.findAll();
	}

	public List<EmployeeSTC> getEmployeeSTC(String dni) {
		return employeeSTCRepository.findByEmployee(dni);
	}

	public void addEmployeeSTC(EmployeeSTC employeeSTC) {
		employeeSTCRepository.insert(employeeSTC);
	}

	public EmployeeSTC getLatestMedition(String dni) {
		List<EmployeeSTC> allSTCMeditions = employeeSTCRepository.findByEmployee(dni);

		EmployeeSTC latest = null;
		for (EmployeeSTC empSTC : allSTCMeditions) {
			if (latest == null) {
				latest = empSTC;
			} else if (empSTC.getDate().compareTo(latest.getDate()) > 0) {
				latest = empSTC;
			}
		}

		return latest;
	}

}
