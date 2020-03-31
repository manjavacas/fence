package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.EmployeeSTC;
import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.service.EmployeeSTCService;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.TeamService;

@RestController
public class EmployeeSTCController {

	@Autowired
	private EmployeeSTCService employeeSTCService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/EmployeesSTC")
	public List<EmployeeSTC> getAllEmployeeSTC() {
		return employeeSTCService.getAllEmployeeSTC();
	}

	@RequestMapping("/EmployeesSTC/{dni}")
	public List<EmployeeSTC> getEmployeeSTC(@PathVariable String dni) {
		return employeeSTCService.getEmployeeSTC(dni);
	}

	@RequestMapping("/EmployeesSTC/project/{project}")
	public List<EmployeeSTC> getEmployeeSTCByProject(@PathVariable String project) {

		List<Team> teamsInProject = teamService.getTeamsByProject(project);

		// Get employees in project
		List<Employee> employeesInProject = new ArrayList<Employee>();
		for (Team team : teamsInProject) {
			employeesInProject.addAll(employeeService.getByTeam(team.getName()));
		}

		// Get latest STC meditions for every employee in project
		List<EmployeeSTC> projectEmployeesSTC = new ArrayList<EmployeeSTC>();
		for (Employee employee : employeesInProject) {
			projectEmployeesSTC.add(employeeSTCService.getLatestMedition(employee.getDni()));
		}

		return projectEmployeesSTC;
	}

	@PostMapping(value = "/EmployeesSTC")
	public void addEmployeeSTC(@RequestBody EmployeeSTC employeeSTC) {
		employeeSTCService.addEmployeeSTC(employeeSTC);
	}

}
