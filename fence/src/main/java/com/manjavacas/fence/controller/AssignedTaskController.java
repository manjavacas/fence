package com.manjavacas.fence.controller;


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

import com.manjavacas.fence.model.AssignedTask;
import com.manjavacas.fence.service.AssignedTaskService;

@RestController
public class AssignedTaskController {

	@Autowired
	private AssignedTaskService assignedTaskService;

	@RequestMapping("/AssignedTasks")
	public List<AssignedTask> getAllAssignedTasks() {
		return assignedTaskService.getAllAssignedTasks();
	}

	@RequestMapping("/AssignedTasks/{id}")
	public AssignedTask getAssignedTask(@PathVariable ObjectId id) {
		return assignedTaskService.getAssignedTask(id);
	}
	
	@RequestMapping("/AssignedTasks/employee/{dni}")
	public List<AssignedTask> getAssignedTasksToEmployee(@PathVariable String dni) {
		return assignedTaskService.getAssignedTasksToEmployee(dni);
	}

	@PostMapping(value = "/AssignedTasks")
	public void addAssignedTask(@RequestBody AssignedTask AssignedTask) {
		assignedTaskService.addAssignedTask(AssignedTask);
	}

	@PutMapping(value = "/AssignedTasks/{id}")
	public void updateAssignedTask(@PathVariable String id, @RequestBody AssignedTask assignedTask) {
		assignedTaskService.updateAssignedTask(id, assignedTask);
	}

	@DeleteMapping("/AssignedTasks/{id}")
	public void deleteAssignedTask(@PathVariable String id) {
		assignedTaskService.deleteAssignedTask(id);
	}
}

