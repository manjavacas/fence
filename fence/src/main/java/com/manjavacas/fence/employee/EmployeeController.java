package com.manjavacas.fence.employee;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/Employees")
	public ArrayList<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@RequestMapping("/Employees/{id}")
	public Employee getEmployee(@PathVariable String id) {
		return null;
	}

	@PostMapping(value = "/Employees")
	public void addEmployee(@RequestBody Employee Employee) {
		employeeService.addEmployee(Employee);
	}

	@PutMapping(value = "/Employees/{id}")
	public void updateEmployee(@PathVariable String id, @RequestBody Employee Employee) {
		employeeService.updateEmployee(id, Employee);
	}

	@DeleteMapping("/Employees/{id}")
	public void deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployee(id);
	}
}
