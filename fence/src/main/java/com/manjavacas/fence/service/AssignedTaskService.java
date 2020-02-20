package com.manjavacas.fence.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.AssignedTask;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.repository.AssignedTaskRepository;

@Service
public class AssignedTaskService {

	@Autowired
	AssignedTaskRepository assignedTaskRepository;

	public List<AssignedTask> getAllAssignedTasks() {
		return assignedTaskRepository.findAll();
	}

	public AssignedTask getAssignedTask(ObjectId id) {
		return assignedTaskRepository.findBy_id(id.toString());
	}

	public List<AssignedTask> getAssignedTasksToEmployee(String dni) {
		return assignedTaskRepository.findTasksByEmployee(dni);
	}

	public List<Employee> getEmployeesAssignedTo(int ref) {
		return assignedTaskRepository.findEmployeesByTask(ref);
	}

	public void addAssignedTask(AssignedTask assignedTask) {
		assignedTaskRepository.insert(assignedTask);
	}

	public void updateAssignedTask(ObjectId id, AssignedTask newAssignedTask) {
		AssignedTask currentAssignedTask = assignedTaskRepository.findBy_id(id.toString());

		currentAssignedTask.setEmployee(newAssignedTask.getEmployee());
		currentAssignedTask.setTask(newAssignedTask.getTask());

		assignedTaskRepository.save(currentAssignedTask);
	}

	public void deleteAssignedTask(ObjectId id) {
		assignedTaskRepository.deleteBy_id(id.toString());
	}

}
