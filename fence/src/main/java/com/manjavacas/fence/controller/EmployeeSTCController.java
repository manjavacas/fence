package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.EmployeeSTC;
import com.manjavacas.fence.service.EmployeeSTCService;

@RestController
public class EmployeeSTCController {

	@Autowired
	private EmployeeSTCService employeeSTCService;

	@RequestMapping("/EmployeesSTC")
	public List<EmployeeSTC> getAllEmployeeSTC() {
		return employeeSTCService.getAllEmployeeSTC();
	}

	@RequestMapping("/EmployeesSTC/{dni}")
	public List<EmployeeSTC> getEmployeeSTC(@PathVariable String dni) {
		return employeeSTCService.getEmployeeSTC(dni);
	}

	@PostMapping(value = "/EmployeesSTC")
	public void addEmployeeSTC(@RequestBody EmployeeSTC employeeSTC) {
		employeeSTCService.addEmployeeSTC(employeeSTC);
	}

}
