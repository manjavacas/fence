package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.manjavacas.fence.model.CA;
import com.manjavacas.fence.model.CR;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.service.CAservice;
import com.manjavacas.fence.service.CRservice;
import com.manjavacas.fence.service.CommunicationService;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.TAservice;
import com.manjavacas.fence.service.TDservice;
import com.manjavacas.fence.service.TaskDependencyService;
import com.manjavacas.fence.service.TaskService;

public class STCMeasurer {

	@Autowired
	TaskService taskService;

	@Autowired
	TaskController taskController;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	TaskDependencyService taskDependencyService;

	@Autowired
	CommunicationService communicationService;

	@Autowired
	TAservice taService;

	@Autowired
	TDservice tdService;

	@Autowired
	CAservice caService;

	@Autowired
	CRservice crService;

	@PutMapping(value = "/STC/{project}/employees")
	public Hashtable<Employee, Double> getEmployeesSTC(@PathVariable String project) {

		Hashtable<Employee, Double> employeesSTC = new Hashtable<Employee, Double>();

		List<TA> taMatrix = calculateTAMatrix(project);
		List<TD> tdMatrix = calculateTDMatrix(project);
		calculateCRMatrix(project, taMatrix, tdMatrix);
		calculateCAMatrix(project);

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

		// Update collection
		taService.updateTA(taskAssignmentMatrix);

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
				// WEIGHT = TASK_DEPENDENCY_VALUE / DEPENDENCY_VALUES_SUM
				double weight = taskDependency.getValue() / sumValues;
				taskDependenciesMatrix.add(new TD(task.getReference(), taskDependency.getTask2(), project, weight));
			}

		}

		// Update collection
		tdService.updateTD(taskDependenciesMatrix);

		return taskDependenciesMatrix;
	}

	private List<CR> calculateCRMatrix(String project, List<TA> taMatrix, List<TD> tdMatrix) {

		List<CR> coordinationRequirementsMatrix = new ArrayList<CR>();

		for (TA ta : taMatrix) {

			String user1 = ta.getUser();

			// Get task dependencies of the assignated task
			List<TaskDependency> dependencies = taskDependencyService.getTaskDependenciesOf(ta.getTask());

			for (TaskDependency td : dependencies) {

				// Get responsibles of each dependency with the assignated task
				List<Employee> responsibles = taskController.getResponsiblesOf(td.getTask2());

				for (Employee user2 : responsibles) {

					// Ignore self-communication
					if (user2.getDni() != user1) {

						// Compute weights
						double weightTA1 = ta.getWeight();
						double weightTA2 = taService.getTAByTaskAndUser(td.getTask2(), user2.getDni()).getWeight();
						double weightTD = tdService.getTDByTask1AndTask2(ta.getTask(), td.getTask2()).getWeight();

						// CR.weight = TA[user1][task] * TA[user2][dependency] * TD[task][dependency]
						double weight = weightTA1 * weightTA2 * weightTD;

						// Apply Global Software Development distances
						weight = computeGlobalFactors(weight, employeeService.getEmployee(user1), user2);

						if (weight > 1) {
							weight = 1;
						}

						coordinationRequirementsMatrix
								.add(new CR(user1, user2.getDni(), project, ta.getTask(), weight));

					}

				}

			}

		}

		// Update collection
		crService.updateCR(coordinationRequirementsMatrix);

		return coordinationRequirementsMatrix;
	}

	private List<CA> calculateCAMatrix(String project) {

		List<CA> actualCommunicationMatrix = new ArrayList<CA>();

		return actualCommunicationMatrix;
	}

	private double computeGlobalFactors(double weight, Employee user1, Employee user2) {

		// Addition coefficient that will be applied
		double coefficient = 0;

		// Common languages
		int common = 0;
		for (String l1 : user1.getLanguages()) {
			for (String l2 : user2.getLanguages()) {
				if (l1 == l2) {
					common++;
				}
			}
		}

		if (common < 1) {
			coefficient += 0.2;
		}

		// Geographical distance
		if (!user1.getCountry().equals(user2.getCountry())) {
			coefficient += 0.2;
		}

		// Temporal distance (UTC+XY)
		int hour1 = Integer.parseInt(user1.getTimezone().substring(4, user1.getTimezone().length()));
		int hour2 = Integer.parseInt(user2.getTimezone().substring(4, user2.getTimezone().length()));

		double temporalDistance = Math.sqrt(Math.pow(hour1, 2) - Math.pow(hour2, 2));

		if (temporalDistance > 8) {
			coefficient += 0.3;
		} else if (temporalDistance > 5) {
			coefficient += 0.2;
		} else if (temporalDistance > 3) {
			coefficient += 0.1;
		}

		// TO-DO
		// Sociocultural distance
		// double scDistance = computeSocioculturalDistance(user1.getCountry(),
		// user2.getCountry());

		return weight + coefficient;
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
