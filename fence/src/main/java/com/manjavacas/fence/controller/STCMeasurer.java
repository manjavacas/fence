package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.CA;
import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.model.CR;
import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.EmployeeSTC;
import com.manjavacas.fence.model.ProjectSTC;
import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.model.TeamSTC;
import com.manjavacas.fence.service.CAservice;
import com.manjavacas.fence.service.CGservice;
import com.manjavacas.fence.service.CRservice;
import com.manjavacas.fence.service.CommunicationService;
import com.manjavacas.fence.service.EmployeeSTCService;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.ProjectSTCService;
import com.manjavacas.fence.service.TAservice;
import com.manjavacas.fence.service.TDservice;
import com.manjavacas.fence.service.TaskDependencyService;
import com.manjavacas.fence.service.TaskService;
import com.manjavacas.fence.service.TeamSTCService;
import com.manjavacas.fence.service.TeamService;

@RestController
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
	TeamService teamService;

	@Autowired
	TAservice taService;

	@Autowired
	TDservice tdService;

	@Autowired
	CAservice caService;

	@Autowired
	CRservice crService;

	@Autowired
	CGservice cgService;

	@Autowired
	EmployeeSTCService employeeSTCservice;

	@Autowired
	TeamSTCService teamSTCservice;

	@Autowired
	ProjectSTCService projectSTCservice;

	@RequestMapping("/STC/{project}")
	public boolean calculateSTC(@PathVariable String project) {

		boolean success = true;

		try {
			List<TA> taMatrix = calculateTAMatrix(project);
			List<TD> tdMatrix = calculateTDMatrix(project);
			List<CR> crMatrix = calculateCRMatrix(project, taMatrix, tdMatrix);
			List<CA> caMatrix = calculateCAMatrix(project, crMatrix);
			List<CG> cgMatrix = calculateCGMatrix(caMatrix, crMatrix);

			calculateSTCEmployees(project, crMatrix, cgMatrix);
			calculateSTCTeams(project, crMatrix, cgMatrix);
			calculateSTCProjects(project, crMatrix, cgMatrix);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
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
			double sumValues = taskDependencies.stream().mapToDouble(TaskDependency::getWeight).sum();

			// Compute weight and save in matrix
			for (TaskDependency taskDependency : taskDependencies) {
				// WEIGHT = TASK_DEPENDENCY_VALUE / DEPENDENCY_VALUES_SUM
				double weight = taskDependency.getWeight() / sumValues;
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
						weight = computeGlobalFactorsCR(weight, employeeService.getEmployee(user1), user2);

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

	private List<CA> calculateCAMatrix(String project, List<CR> crMatrix) {

		List<CA> actualCommunicationMatrix = new ArrayList<CA>();

		// Get different employees from CR matrix
		List<String> users = new ArrayList<String>();
		for (CR cr : crMatrix) {
			if (!users.contains(cr.getUser1()))
				users.add(cr.getUser1());
			if (!users.contains(cr.getUser2()))
				users.add(cr.getUser2());
		}

		// Check communications for each employee
		for (String user : users) {
			double weightCA = 0;

			// Get tasks assigned to user
			List<Task> userTasks = new ArrayList<Task>();
			for (Task task : taskService.getPendingTasksByProject(project)) {
				if (task.getAssigned_to().contains(user)) {
					userTasks.add(task);
				}
			}

			if (userTasks.size() > 0) {

				// Get user communications
				List<Communication> userCommunications = communicationService.getCommunicationsByUser1(user);

				for (Communication communication : userCommunications) {

					String user2 = communication.getUser2();

					// Get user tasks dependencies for which user1 and user2 must communicate
					List<Task> userDependencies = new ArrayList<Task>();
					List<TaskDependency> taskDependencies = new ArrayList<TaskDependency>();
					Task dependency = null;
					for (Task task : userTasks) {
						taskDependencies = taskDependencyService.getTaskDependenciesOf(task.getReference());
						for (TaskDependency td : taskDependencies) {
							dependency = taskService.getTask(td.getTask2());
							if (dependency.getAssigned_to().contains(user2)) {
								userDependencies.add(dependency);
							}
						}
					}

					int totalDependencies = userDependencies.size();

					if (totalDependencies > 0) {

						// Communications between user1 and user 2
						List<Communication> communications = communicationService.getCommunicationsBetween(user, user2);
						int totalCommunications = communications.size();

						// Communications for the given task
						int taskCommunications = 0;
						for (Communication com : communications) {
							if (com.getTaskRef().equals(communication.getTaskRef())) {
								taskCommunications++;
							}
						}

						if (taskCommunications > 0) {

							double frequency;

							// Task communications frequency
							if (taskCommunications >= 10) {
								frequency = 1; // VERY HIGH
							} else if (taskCommunications >= 8) {
								frequency = 0.8; // HIGH
							} else if (taskCommunications >= 6) {
								frequency = 0.6; // NORMAL
							} else if (taskCommunications >= 4) {
								frequency = 0.4; // LOW
							} else {
								frequency = 0.2; // VERY LOW
							}

							// Weight calculation
							weightCA += (frequency * 1 / totalDependencies) / totalCommunications;

							// Apply GSD increments
							weightCA = computeGlobalFactorsCA(weightCA, communication);

							actualCommunicationMatrix
									.add(new CA(user, user2, communication.getTaskRef(), project, weightCA));
						}

					}
				}

			}
		}

		// Update collection
		caService.updateCA(actualCommunicationMatrix);

		return actualCommunicationMatrix;
	}

	private List<CG> calculateCGMatrix(List<CA> caMatrix, List<CR> crMatrix) {

		List<CG> coordinationGapMatrix = new ArrayList<CG>();

		double weightCA;
		double weightCR;
		double weightCG;

		for (CR cr : crMatrix) {

			// Find a CA that covers the current CR
			List<CA> caList = caService.getCA(cr.getUser1(), cr.getUser2(), cr.getProject(), cr.getTask());

			if (caList.size() > 0) {

				// Sum communications
				weightCA = 0;
				for (CA ca : caList) {
					weightCA += ca.getWeight();
				}

				weightCR = cr.getWeight();

				// Compute CG weight
				weightCG = weightCR - weightCA;

				if (weightCG > 0) {
					coordinationGapMatrix
							.add(new CG(cr.getUser1(), cr.getUser2(), cr.getTask(), cr.getProject(), weightCG));
				}

			} else {
				coordinationGapMatrix
						.add(new CG(cr.getUser1(), cr.getUser2(), cr.getTask(), cr.getProject(), cr.getWeight()));
			}

		}

		// Update collection
		cgService.updateCG(coordinationGapMatrix);

		return coordinationGapMatrix;
	}

	private void calculateSTCEmployees(String project, List<CR> crMatrix, List<CG> cgMatrix) {

		List<EmployeeSTC> stcEmployees = new ArrayList<EmployeeSTC>();

		// Get employees in project
		List<Employee> allProjectEmployees = new ArrayList<Employee>();
		List<Employee> allEmployees = employeeService.getAllEmployees();
		List<Team> allProjectTeams = teamService.getTeamsByProject(project);
		Team team = null;
		for (Employee employee : allEmployees) {
			team = teamService.getTeam(employee.getTeam());
			if (allProjectTeams.contains(team)) {
				allProjectEmployees.add(employee);
			}
		}

		List<CR> employeeCRs = null;
		List<CG> employeeCGs = null;

		// Calculate STC level by employee
		for (Employee employee : allProjectEmployees) {

			employeeCRs = crService.getCRByUser1(employee.getDni());
			employeeCGs = cgService.getCGByUser1(employee.getDni());

			// Get CR sum
			double employeeCRsum = 0;
			for (CR cr : employeeCRs) {
				employeeCRsum += cr.getWeight();
			}

			// Get CG sum
			double employeeCGsum = 0;
			for (CG cg : employeeCGs) {
				employeeCGsum += cg.getWeight();
			}

			// Calculate STC
			if (employeeCGsum <= 0) {
				stcEmployees.add(new EmployeeSTC(employee.getDni(), 100, new Date()));
			} else {
				double stcUser = 1 - employeeCGsum / employeeCRsum;
				stcEmployees.add(new EmployeeSTC(employee.getDni(), stcUser * 100, new Date()));
			}

		}

		// Save in database
		for (EmployeeSTC stcEmployee : stcEmployees) {
			employeeSTCservice.addEmployeeSTC(stcEmployee);
		}

	}

	private void calculateSTCTeams(String project, List<CR> crMatrix, List<CG> cgMatrix) {

		List<TeamSTC> stcTeams = new ArrayList<TeamSTC>();

		List<Team> allProjectTeams = teamService.getTeamsByProject(project);

		// Get different employees from CR matrix
		List<String> users = new ArrayList<String>();
		for (CR cr : crMatrix) {
			if (!users.contains(cr.getUser1()))
				users.add(cr.getUser1());
			if (!users.contains(cr.getUser2()))
				users.add(cr.getUser2());
		}

		for (Team team : allProjectTeams) {

			double teamCRsum = 0;
			double teamCGsum = 0;

			// Get users in the current team
			List<Employee> teamUsers = employeeService.getByTeam(team.getName());

			// Sum CRs and CGs weights
			for (Employee employee : teamUsers) {

				List<CR> employeeCRs = crService.getCRByUser1(employee.getDni());
				for (CR cr : employeeCRs) {
					teamCRsum += cr.getWeight();
				}

				List<CG> employeeCGs = cgService.getCGByUser1(employee.getDni());
				for (CG cg : employeeCGs) {
					teamCGsum += cg.getWeight();
				}

			}

			// Calculate STC
			if (teamCGsum <= 0) {
				stcTeams.add(new TeamSTC(team.getName(), 100, new Date()));
			} else {
				double stcTeam = 1 - teamCGsum / teamCRsum;
				stcTeams.add(new TeamSTC(team.getName(), stcTeam * 100, new Date()));
			}

		}

		// Save in database
		for (TeamSTC stcTeam : stcTeams) {
			teamSTCservice.addTeamSTC(stcTeam);
		}

	}

	private void calculateSTCProjects(String project, List<CR> crMatrix, List<CG> cgMatrix) {

		ProjectSTC stcProject;

		// Sum CR weights
		double sumCRproject = 0;
		for (CR cr : crMatrix) {
			sumCRproject += cr.getWeight();
		}

		// Sum CG weights
		double sumCGproject = 0;
		for (CG cg : cgMatrix) {
			sumCGproject += cg.getWeight();
		}

		// Calculate STC
		if (sumCGproject <= 0) {
			stcProject = new ProjectSTC(project, 100, new Date());
		} else {
			double stc = 1 - sumCGproject / sumCRproject;
			stcProject = new ProjectSTC(project, stc * 100, new Date());
		}

		// Save in database
		projectSTCservice.addProjectSTC(stcProject);

	}

	private double computeGlobalFactorsCR(double weight, Employee user1, Employee user2) {

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

		// TODO: Check sign: UTC+1, UTC-6, etc.
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

	private double computeGlobalFactorsCA(double weight, Communication communication) {
		return 0;
	}

}
