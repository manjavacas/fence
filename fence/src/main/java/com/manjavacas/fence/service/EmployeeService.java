package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(String dni) {
		return employeeRepository.findByDni(dni);
	}

	public void addEmployee(Employee employee) {
		employeeRepository.insert(employee);
	}

	public void updateEmployee(String dni, Employee newEmployee) {
		Employee currentEmployee = employeeRepository.findByDni(dni);
		
		currentEmployee.setDni(newEmployee.getDni());
		currentEmployee.setName(newEmployee.getName());
		currentEmployee.setEmail(newEmployee.getEmail());
		currentEmployee.setGenre(newEmployee.getGenre());
		currentEmployee.setAge(newEmployee.getAge());
		currentEmployee.setRole(newEmployee.getRole());
		currentEmployee.setCountry(newEmployee.getCountry());
		currentEmployee.setExperience(newEmployee.getExperience());
		currentEmployee.setTeam(newEmployee.getTeam());
		currentEmployee.setStc(newEmployee.getStc());

		employeeRepository.save(currentEmployee);
	}

	public void deleteEmployee(String dni) {
		employeeRepository.deleteByDni(dni);
	}

}