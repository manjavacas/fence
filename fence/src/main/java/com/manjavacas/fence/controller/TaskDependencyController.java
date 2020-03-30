package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.service.TaskDependencyService;

@RestController
public class TaskDependencyController {

	@Autowired
	private TaskDependencyService taskDependencyService;

	@RequestMapping("/TaskDependencies")
	public List<TaskDependency> getAllTaskDependencies() {
		return taskDependencyService.getAllTaskDependencies();
	}

	@RequestMapping("/TaskDependencies/{reference}")
	public List<TaskDependency> getTaskDependenciesOf(@PathVariable String reference) {
		return taskDependencyService.getTaskDependenciesOf(reference);
	}

	@PutMapping(value = "/TaskDependencies/{reference}")
	public void updateTaskDependency(@PathVariable String reference, @RequestBody TaskDependency task) {
		taskDependencyService.updateTaskDependency(reference, task);
	}

	@DeleteMapping("/TaskDependencies/{reference}")
	public void deleteTaskDependency(@PathVariable String reference) {
		taskDependencyService.deleteTaskDependency(reference);
	}
}
