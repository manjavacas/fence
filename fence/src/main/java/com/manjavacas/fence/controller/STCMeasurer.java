package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;

import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.model.CommunicationNeeding;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Task;

public class STCMeasurer {

	@Autowired
	TaskController taskController;

	@Autowired
	CommunicationController communicationController;

	@Autowired
	EmployeeController employeeController;

	@PutMapping(value = "/STC/employees")
	public Hashtable<Employee, Double> getEmployeesSTC() {

		Hashtable<Employee, Double> employeesSTC = new Hashtable<Employee, Double>();

		List<Task> tasks = taskController.getAllTasks();
		List<Employee> employees = employeeController.getAllEmployees();
		List<Communication> communications = communicationController.getAllCommunications();

		// TASKS ASSIGNMENTS MATRIX (TA)
		Hashtable<Task, List<Employee>> TA = new Hashtable<Task, List<Employee>>();
		for (Task task : tasks) {
			List<Employee> users = taskController.getResponsiblesOf(task.getReference());
			TA.put(task, users);
		}

		// TASKS DEPENDENCIES MATRIX (TD)
		Hashtable<Task, List<Task>> TD = new Hashtable<Task, List<Task>>();
		for (Task task : tasks) {
			List<Task> dependencies = taskController.getDependenciesOf(task.getReference());
			TD.put(task, dependencies);
		}

		// ACTUAL COMMUNICATION MATRIX (CA)
		Hashtable<Employee, List<Employee>> CA = new Hashtable<Employee, List<Employee>>();
		for (Employee employee : employees) {
			ArrayList<Employee> communicatesWith = new ArrayList<Employee>();
			for (Communication communication : communications) {
				if (employee.getDni() == communication.getUser1()) {
					communicatesWith.add(employeeController.getEmployee(communication.getUser2()));
				} else if (employee.getDni() == communication.getUser2()) {
					communicatesWith.add(employeeController.getEmployee(communication.getUser1()));
				}
			}
			CA.put(employee, communicatesWith);
		}

		// COMMUNICATION REQUIREMENTS MATRIX (CR)
		Hashtable<Employee, List<Employee>> CR = new Hashtable<Employee, List<Employee>>();
		for (Employee employee : employees) {
			ArrayList<Employee> communicationNeedings = new ArrayList<Employee>();
			for (Task task : tasks) {
				if (task.getAssigned_to().contains(employee.getDni())) {

					// Get task dependencies
					List<Task> dependencies = taskController.getDependenciesOf(task.getReference());

					// Get responsibles and add them to communication needings
					for (Task dependency : dependencies) {
						communicationNeedings.addAll(taskController.getResponsiblesOf(dependency.getReference()));
					}
				}
			}
			CR.put(employee, communicationNeedings);
		}

		// LACK OF COORDINATION MATRIX (G)
		Hashtable<Employee, List<CommunicationNeeding>> G = new Hashtable<Employee, List<CommunicationNeeding>>();
		for (Employee employee : employees) {

			ArrayList<CommunicationNeeding> Gij = new ArrayList<CommunicationNeeding>();

			List<Employee> communicationNeedings = CR.get(employee);
			List<Employee> actualCommunications = CA.get(employee);

			// Get unique employees
			List<Employee> partners = communicationNeedings.stream().distinct().collect(Collectors.toList());

			for (Employee partner : partners) {
				double CRij = Collections.frequency(communicationNeedings, partner);
				double CAij = Collections.frequency(actualCommunications, partner);

				Gij.add(new CommunicationNeeding(partner, CRij - CAij));

			}

			G.put(employee, Gij);
		}

		// CALCULATE EMPLOYEES STC
		
		return employeesSTC;

	}

	@PutMapping(value = "/STC/projects")
	public void getProjectsSTC() {

	}
}
