package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.TaskDependencyService;
import com.manjavacas.fence.service.TaskService;

public class STCMeasurer {

	@Autowired
	TaskService taskService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	TaskDependencyService taskDependencyService;

	@PutMapping(value = "/STC/{project}/employees")
	public Hashtable<Employee, Double> getEmployeesSTC(@PathVariable String project) {

		Hashtable<Employee, Double> employeesSTC = new Hashtable<Employee, Double>();

		calculateTAMatrix(project);
		calculateTDMatrix(project);

		return employeesSTC;

	}

	private List<TA> calculateTAMatrix(String project) {

		List<TA> taskAssignmentMatrix = new ArrayList<TA>();
		List<Task> tasks = taskService.getPendingTasksByProject(project);

		for (Task task : tasks) {

			// Get employees assigned to task
			List<Employee> users = new ArrayList<Employee>();
			for (String id : task.getAssigned_to()) {
				users.add(employeeService.getEmployee(id));
			}

			// Compute experience summatory
			double sumExp = users.stream().mapToDouble(Employee::getExperienceNum).sum();

			// Compute weight and save in matrix
			for (Employee user : users) {
				// WEIGHT = (USER_EXPERIENCE / USERS_EXPERIENCE_SUM) * TASK_PRIORITY
				// Every employee contributes to the task according to his/her USER_EXPERIENCE
				// The TASK_PRIORITY affects to this contribution percentage
				double weight = (user.getExperienceNum() / sumExp) * task.getPriorityNum();

				if (weight > 1) {
					weight = 1;
				}

				taskAssignmentMatrix.add(new TA(user.getDni(), task.getReference(), project, weight));
			}

		}

		return taskAssignmentMatrix;
	}

	private List<TD> calculateTDMatrix(String project) {

		List<TD> taskDependenciesMatrix = new ArrayList<TD>();
		List<Task> tasks = taskService.getPendingTasksByProject(project);

		for (Task task : tasks) {

			// Get tasks dependencies within a project
			List<TaskDependency> taskDependencies = taskDependencyService.getTaskDependenciesOf(task.getReference());

			// Compute dependency values summatory
			double sumValues = taskDependencies.stream().mapToDouble(TaskDependency::getValue).sum();

			// Compute weight and save in matrix
			for (TaskDependency taskDependency : taskDependencies) {
				double weight = taskDependency.getValue() / sumValues;
				taskDependenciesMatrix.add(new TD(task.getReference(), taskDependency.getTask2(), project, weight));
			}

		}

		return taskDependenciesMatrix;
	}

	// Get list of STC level by team
	@PutMapping(value = "/STC/{project}/teams")
	public Hashtable<Team, Double> getProjectTotalSTC(@PathVariable String project) {
		return null;
	}

	// Get total project STC level
	@PutMapping(value = "/STC/{project}")
	public double getProjectSTC(@PathVariable String project) {
		return 99999;
	}
}
