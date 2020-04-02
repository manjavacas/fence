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

	@RequestMapping("/TaskDependencies/{task}")
	public List<TaskDependency> getDependenciesOf(@PathVariable String task) {
		return taskDependencyService.getDependenciesOf(task);
	}

	@RequestMapping("/TaskDependencies/{task1}/{task2}")
	public TaskDependency getTaskDependency(@PathVariable String task1, String task2) {
		return taskDependencyService.getTaskDependency(task1, task2);
	}

	@PutMapping(value = "/TaskDependencies/{task1}/{task2}")
	public void updateTaskDependency(@PathVariable String task1, String task2,
			@RequestBody TaskDependency taskDependency) {
		taskDependencyService.updateTaskDependency(task1, task2, taskDependency);
	}

	@DeleteMapping("/TaskDependencies/{task1}/{task2}")
	public void deleteTaskDependency(@PathVariable String task1, String task2) {
		taskDependencyService.deleteTaskDependency(task1, task2);
	}

}
