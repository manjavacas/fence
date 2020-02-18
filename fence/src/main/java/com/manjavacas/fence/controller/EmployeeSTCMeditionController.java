package com.manjavacas.fence.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.EmployeeSTCMedition;
import com.manjavacas.fence.service.EmployeeSTCMeditionService;

@RestController
public class EmployeeSTCMeditionController {

	@Autowired
	private EmployeeSTCMeditionService employeeSTCMeditionService;

	@RequestMapping("/EmployeeSTCMeditions")
	public List<EmployeeSTCMedition> getAllEmployeeSTCMeditions() {
		return employeeSTCMeditionService.getAllEmployeeSTCMeditions();
	}

	@RequestMapping("/EmployeeSTCMeditions/{id}")
	public EmployeeSTCMedition getEmployeeSTCMedition(@PathVariable ObjectId id) {
		return employeeSTCMeditionService.getEmployeeSTCMedition(id);
	}

	@RequestMapping("/EmployeeSTCMeditions/employee/{id}")
	public List<EmployeeSTCMedition> getEmployeeSTCMeditionsByDni(@PathVariable String dni) {
		return employeeSTCMeditionService.getEmployeeSTCMeditionsByDni(dni);
	}

	@RequestMapping("/EmployeeSTCMeditions/date/{dateStr}")
	public List<EmployeeSTCMedition> getEmployeeSTCMeditionsByDate(@PathVariable String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		return employeeSTCMeditionService.getEmployeeSTCMeditionsByDate(date);
	}

	@PostMapping(value = "/EmployeeSTCMeditions")
	public void addEmployeeSTCMedition(@RequestBody EmployeeSTCMedition EmployeeSTCMedition) {
		employeeSTCMeditionService.addEmployeeSTCMedition(EmployeeSTCMedition);
	}

	@PutMapping(value = "/EmployeeSTCMedition/{id}")
	public void updateEmployeeSTCMedition(@PathVariable ObjectId id,
			@RequestBody EmployeeSTCMedition employeeSTCMedition) {
		employeeSTCMeditionService.updateEmployeeSTCMedition(id, employeeSTCMedition);
	}

	@DeleteMapping("/EmployeeSTCMeditions/{id}")
	public void deleteEmployeeSTCMedition(@PathVariable ObjectId id) {
		employeeSTCMeditionService.deleteEmployeeSTCMedition(id);
	}
}
