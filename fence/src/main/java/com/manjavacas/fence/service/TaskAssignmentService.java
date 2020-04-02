package com.manjavacas.fence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.model.TaskAssignment;
import com.manjavacas.fence.repository.TaskAssignmentRepository;

@Service
public class TaskAssignmentService {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskAssignmentRepository taskAssignmentRepository;

	public List<TaskAssignment> getAllTaskAssignments() {
		return taskAssignmentRepository.findAll();
	}

	public TaskAssignment getTaskAssignment(String task, String user) {
		return taskAssignmentRepository.findByTaskAndUser(task, user);
	}

	public List<Employee> getEmployeesAssignedTo(String task) {
		List<TaskAssignment> taAssignments = taskAssignmentRepository.findByTask(task);
		List<Employee> employees = new ArrayList<Employee>();
		for (TaskAssignment ta : taAssignments) {
			employees.add(employeeService.getEmployee(ta.getUser()));
		}
		return employees;
	}
	
	public List<String> getEmployeesIdsAssignedTo(String task) {
		List<TaskAssignment> taAssignments = taskAssignmentRepository.findByTask(task);
		List<String> employeesIds = new ArrayList<String>();
		for (TaskAssignment ta : taAssignments) {
			employeesIds.add(ta.getUser());
		}
		return employeesIds;
	}
	
	public List<Task> getTasksAssignedTo(String user) {
		List<TaskAssignment> taAssignments = taskAssignmentRepository.findByUser(user);
		List<Task> tasks = new ArrayList<Task>();
		for (TaskAssignment ta : taAssignments) {
			tasks.add(taskService.getTask(ta.getTask()));
		}
		return tasks;
	}

	public void updateTaskAssignment(String task, String user, TaskAssignment newTaskAssignment) {
		TaskAssignment currentTaskAssignment = taskAssignmentRepository.findByTaskAndUser(task, user);

		if (currentTaskAssignment == null) {
			currentTaskAssignment = new TaskAssignment();
		}

		currentTaskAssignment.setTask(newTaskAssignment.getTask());
		currentTaskAssignment.setUser(newTaskAssignment.getUser());
		currentTaskAssignment.setProject(newTaskAssignment.getProject());

		taskAssignmentRepository.save(currentTaskAssignment);
	}

	public void deleteTaskAssignment(String task, String user) {
		taskAssignmentRepository.deleteByTaskAndUser(task, user);
	}

}
