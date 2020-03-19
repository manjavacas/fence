package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/Tasks")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@RequestMapping("/Tasks/{reference}")
	public Task getTask(@PathVariable String reference) {
		return taskService.getTask(reference);
	}

	@RequestMapping("/Tasks/project/{project}")
	public List<Task> getTasksByProject(@PathVariable String project) {
		return taskService.getTasksByProject(project);
	}

	@RequestMapping("/Tasks/pending/{project}")
	public List<Task> getPendingTasksByProject(@PathVariable String project) {
		return taskService.getPendingTasksByProject(project);
	}

	@RequestMapping("/Tasks/{reference}/responsibles")
	public List<Employee> getResponsiblesOf(@PathVariable String reference) {

		List<String> responsiblesIds = taskService.getTask(reference).getAssigned_to();
		List<Employee> responsibles = new ArrayList<Employee>();

		for (String dni : responsiblesIds) {
			responsibles.add(employeeService.getEmployee(dni));
		}

		return responsibles;
	}

	@PutMapping(value = "/Tasks/{reference}")
	public void updateTask(@PathVariable String reference, @RequestBody Task task) {
		taskService.updateTask(reference, task);
	}

	@DeleteMapping("/Tasks/{reference}")
	public void deleteTask(@PathVariable String reference) {
		taskService.deleteTask(reference);
	}

}
