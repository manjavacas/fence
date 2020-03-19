package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.repository.TaskDependencyRepository;

public class TaskDependencyService {

	@Autowired
	private TaskDependencyRepository taskDependencyRepository;

	public List<TaskDependency> getAllTaskDependencies() {
		return taskDependencyRepository.findAll();
	}

	public List<TaskDependency> getTaskDependenciesOf(String ref) {
		return taskDependencyRepository.findByTask1(ref);
	}

}
