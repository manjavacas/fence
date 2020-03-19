package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/TaskDependencies/{ref}")
	public List<TaskDependency> getTaskDependenciesOf(@PathVariable String ref) {
		return taskDependencyService.getTaskDependenciesOf(ref);
	}
}
