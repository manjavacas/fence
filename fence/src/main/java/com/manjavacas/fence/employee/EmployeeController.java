package com.manjavacas.fence.employee;

import java.util.List;

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
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@RequestMapping("/Employees/{dni}")
	public Employee getEmployee(@PathVariable String dni) {
		return employeeService.getEmployee(dni);
	}

	@PostMapping(value = "/Employees")
	public void addEmployee(@RequestBody Employee Employee) {
		employeeService.addEmployee(Employee);
	}

	@PutMapping(value = "/Employees/{dni}")
	public void updateEmployee(@PathVariable String dni, @RequestBody Employee Employee) {
		employeeService.updateEmployee(dni, Employee);
	}

	@DeleteMapping("/Employees/{dni}")
	public void deleteEmployee(@PathVariable String dni) {
		employeeService.deleteEmployee(dni);
	}
}