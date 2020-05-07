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
import com.manjavacas.fence.service.TAService;
import com.manjavacas.fence.service.TDService;
import com.manjavacas.fence.service.TaskAssignmentService;
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
	CommunicationService communicationService;

	@Autowired
	TeamService teamService;

	@Autowired
	TAService taService;

	@Autowired
	TDService tdService;

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

	@Autowired
	TaskAssignmentService taskAssignmentService;

	@Autowired
	TaskDependencyService taskDependencyService;

	@RequestMapping("/STC/{project}")
	public boolean calculateSTC(@PathVariable String project) {

		boolean success = true;

		System.out.println("[STC] Starting STC measurement...");
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

		System.out.println("[STC] Done!");
		return success;
	}

	private List<TA> calculateTAMatrix(String project) {

		System.out.println("[STC] Calculating task assignments...");

		List<TA> taskAssignmentMatrix = new ArrayList<TA>();
		List<Task> tasks = taskService.getPendingTasksByProject(project);

		for (Task task : tasks) {

			// Get employees assigned to task
			List<Employee> users = taskAssignmentService.getEmployeesAssignedTo(task.getReference());

			if (users.size() > 0) {

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

		}

		// Update collection
		taService.updateTAs(taskAssignmentMatrix);

		return taskAssignmentMatrix;
	}

	private List<TD> calculateTDMatrix(String project) {

		System.out.println("[STC] Calculating task dependencies...");

		List<TD> taskDependenciesMatrix = new ArrayList<TD>();
		List<Task> tasks = taskService.getPendingTasksByProject(project);

		for (Task task : tasks) {

			// Get tasks dependencies within a project
			List<TaskDependency> taskDependencies = taskDependencyService.getDependenciesOf(task.getReference());

			if (taskDependencies.size() != 0) {
				
				// Compute dependency values summatory
				double sumValues = taskDependencies.stream().mapToDouble(TaskDependency::getValueWeight).sum();

				// Compute weight and save in matrix
				for (TaskDependency taskDependency : taskDependencies) {
					
					// WEIGHT = TASK_DEPENDENCY_VALUE / DEPENDENCY_VALUES_SUM
					double weight = taskDependency.getValueWeight() / sumValues;
					
					taskDependenciesMatrix.add(new TD(task.getReference(), taskDependency.getTask2(), project, weight));
				}
			}

		}

		// Update collection
		tdService.updateTDs(taskDependenciesMatrix);

		return taskDependenciesMatrix;
	}

	private List<CR> calculateCRMatrix(String project, List<TA> taMatrix, List<TD> tdMatrix) {

		System.out.println("[STC] Calculating coordination requirements...");

		List<CR> coordinationRequirementsMatrix = new ArrayList<CR>();

		for (TA ta : taMatrix) {

			String user1 = ta.getUser();

			// Get task dependencies of the assigned task
			List<TaskDependency> dependencies = taskDependencyService.getDependenciesOf(ta.getTask());

			for (TaskDependency td : dependencies) {

				// Get responsibles of each dependency with the assignated task
				List<Employee> responsibles = taskAssignmentService.getEmployeesAssignedTo(td.getTask2());

				for (Employee user2 : responsibles) {

					// Ignore self-communication
					if (!user2.getDni().equals(user1)) {

						// Compute weights
						double weightTA1 = ta.getWeight();
						double weightTA2 = taService.getTAByTaskAndUser(td.getTask2(), user2.getDni()).getWeight();
						double weightTD = tdService.getTDByTask1AndTask2(ta.getTask(), td.getTask2()).getWeight();

						// CR.weight = TA[user1][task] * TA[user2][dependency] * TD[task][dependency]
						double weight = weightTA1 * weightTA2 * weightTD;
						
						// Apply sociocultural factors
						weight += computeGlobalFactors(employeeService.getEmployee(user1), user2);

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

		System.out.println("[STC] Calculating actual coordination...");

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
			List<Task> userTasks = taskAssignmentService.getTasksAssignedTo(user);

			// Get user communications (bidirectional)
			List<Communication> userCommunications = communicationService.getCommunicationsByUser1OrUser2(user);

			for (Communication communication : userCommunications) {

				// Get second user
				String user2 = null;
				if (communication.getUser1().equals(user)) {
					user2 = communication.getUser2();
				} else {
					user2 = communication.getUser1();
				}

				// Get the number of tasks dependencies for which user1 and user2 must
				// communicate
				int totalDependencies = 0;
				List<TaskDependency> taskDependencies = new ArrayList<TaskDependency>();
				for (Task task : userTasks) {
					taskDependencies = taskDependencyService.getDependenciesOf(task.getReference());
					for (TaskDependency td : taskDependencies) {
						if (taskAssignmentService.getEmployeesIdsAssignedTo(td.getTask2()).contains(user2)) {
							totalDependencies++;
						}
					}
				}

				if (totalDependencies > 0) {

					// Communications between user1 and user 2
					List<Communication> communications = communicationService.getCommunicationsBetween(user, user2);
					int totalCommunications = communications.size();

					// Communications for the given tasks
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

						// Apply sociocultural factors
						weightCA -= computeGlobalFactors(employeeService.getEmployee(user),
								employeeService.getEmployee(user2));

						actualCommunicationMatrix
								.add(new CA(user, user2, communication.getTaskRef(), project, weightCA));
					}

				}

			}
		}

		// Update collection
		caService.updateCA(actualCommunicationMatrix);

		return actualCommunicationMatrix;
	}

	private List<CG> calculateCGMatrix(List<CA> caMatrix, List<CR> crMatrix) {

		System.out.println("[STC] Calculating coordination gaps...");

		List<CG> coordinationGapMatrix = new ArrayList<CG>();

		for (CR cr : crMatrix) {

			// Find CAs that cover the current CR
			List<CA> caList = caService.getCA(cr.getUser1(), cr.getUser2(), cr.getProject(), cr.getTask());

			if (caList.size() > 0) {

				// Sum communications
				double weightCA = caList.stream().mapToDouble(CA::getWeight).sum();

				// Get coordination needed
				double weightCR = cr.getWeight();

				// Compute gap weight
				double weightCG = weightCR - weightCA;

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
		
		for (Employee employee : allEmployees) {
			for (Team team : allProjectTeams) {
				if (team.getName().equals(employee.getTeam())) {
					allProjectEmployees.add(employee);
					break;
				}
			}
		}

		List<CR> employeeCRs = null;
		List<CG> employeeCGs = null;

		// Calculate STC level by employee
		for (Employee employee : allProjectEmployees) {

			System.out.println("[EMPLOYEE STC] Calculating " + employee.getDni() + " STC...");

			employeeCRs = crService.getCRByUser1(employee.getDni());
			employeeCGs = cgService.getCGByUser1(employee.getDni());

			// Get CR sum
			double employeeCRsum = employeeCRs.stream().mapToDouble(CR::getWeight).sum();

			// Get CG sum
			double employeeCGsum = employeeCGs.stream().mapToDouble(CG::getWeight).sum();

			// Calculate STC
			if (employeeCGsum <= 0) {
				stcEmployees.add(new EmployeeSTC(employee.getDni(), 100.0, new Date()));
			} else {
				double stcUser = 1 - (double) employeeCGsum / employeeCRsum;
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

			System.out.println("[TEAM STC] Calculating " + team.getName() + " team STC...");

			double teamCRsum = 0;
			double teamCGsum = 0;

			// Get users in the current team
			List<Employee> teamUsers = employeeService.getByTeam(team.getName());

			// Sum CRs and CGs weights
			for (Employee employee : teamUsers) {

				// Sum CR weights
				List<CR> employeeCRs = crService.getCRByUser1(employee.getDni());
				teamCRsum = employeeCRs.stream().mapToDouble(CR::getWeight).sum();

				// Sum CG weights
				List<CG> employeeCGs = cgService.getCGByUser1(employee.getDni());
				teamCGsum = employeeCGs.stream().mapToDouble(CG::getWeight).sum();

			}

			// Calculate STC
			if (teamCGsum <= 0) {
				stcTeams.add(new TeamSTC(team.getName(), 100.0, new Date()));
			} else {
				double stcTeam = 1 - (double) teamCGsum / teamCRsum;
				stcTeams.add(new TeamSTC(team.getName(), stcTeam * 100, new Date()));
			}

		}

		// Save in database
		for (TeamSTC stcTeam : stcTeams) {
			teamSTCservice.addTeamSTC(stcTeam);
		}

	}

	private void calculateSTCProjects(String project, List<CR> crMatrix, List<CG> cgMatrix) {

		System.out.println("[PROJECT STC] Calculating " + project + " project STC...");

		ProjectSTC stcProject;

		// Sum CR weights
		double sumCRproject = crMatrix.stream().mapToDouble(CR::getWeight).sum();

		// Sum CG weights
		double sumCGproject = cgMatrix.stream().mapToDouble(CG::getWeight).sum();

		// Calculate STC
		if (sumCGproject <= 0) {
			stcProject = new ProjectSTC(project, 100.0, new Date());
		} else {
			double stc = 1 - (double) sumCGproject / sumCRproject;
			stcProject = new ProjectSTC(project, stc * 100, new Date());
		}

		// Save in database
		projectSTCservice.addProjectSTC(stcProject);

	}

	private double computeGlobalFactors(Employee user1, Employee user2) {
		return 0;
	}

}
