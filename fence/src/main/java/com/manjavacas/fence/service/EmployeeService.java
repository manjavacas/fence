package com.manjavacas.fence.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.repository.EmployeeRepository;

@Service
public class EmployeeService {

	final static String SUPERVISOR_ALERT = "[WARNING] No proper supervisor is available for the given employee!";

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TeamService teamService;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployee(String dni) {
		return employeeRepository.findByDni(dni);
	}

	public List<Employee> getByTeam(String team) {
		return employeeRepository.findByTeam(team);
	}

	public String getSupervisor(String team) {

		String supervisor = "";

		List<Employee> candidates = employeeRepository.findByTeamAndExperience(team, "VERY HIGH");

		if (candidates.size() == 0) {
			candidates = employeeRepository.findByTeamAndExperience(team, "HIGH");
		}

		if (candidates.size() == 0) {
			candidates = employeeRepository.findByTeamAndExperience(team, "MEDIUM");
		}

		if (candidates.size() == 0) {
			// If no high-experience supervisor is available, return a warning message
			supervisor = SUPERVISOR_ALERT;
		} else {
			// Else, return a random candidate to supervise
			supervisor = candidates.get(new Random().nextInt(candidates.size())).getName();
		}

		return supervisor;

	}

	public void updateEmployee(String dni, Employee newEmployee) {
		Employee currentEmployee = employeeRepository.findByDni(dni);

		if (currentEmployee == null) {
			currentEmployee = new Employee();
		}

		currentEmployee.setDni(newEmployee.getDni());
		currentEmployee.setName(newEmployee.getName());
		currentEmployee.setEmail(newEmployee.getEmail());
		currentEmployee.setAge(newEmployee.getAge());
		currentEmployee.setRole(newEmployee.getRole());
		currentEmployee.setTimezone(newEmployee.getTimezone());
		currentEmployee.setCountry(newEmployee.getCountry());
		currentEmployee.setEnglishLevel(newEmployee.getEnglishLevel());
		currentEmployee.setExperience(newEmployee.getExperience());
		currentEmployee.setTeam(newEmployee.getTeam());

		employeeRepository.save(currentEmployee);
	}

	public void deleteEmployee(String dni) {
		employeeRepository.deleteByDni(dni);
	}

}