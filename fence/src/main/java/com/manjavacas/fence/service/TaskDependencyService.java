package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.repository.TaskDependencyRepository;

@Service
public class TaskDependencyService {

	@Autowired
	TaskDependencyRepository taskDependencyRepository;

	public List<TaskDependency> getAllTaskDependencies() {
		return taskDependencyRepository.findAll();
	}

	public List<TaskDependency> getDependenciesOf(String task) {
		return taskDependencyRepository.findByTask1(task);
	}

	public TaskDependency getTaskDependency(String task1, String task2) {
		return taskDependencyRepository.findByTask1AndTask2(task1, task2);
	}

	public void updateTaskDependency(String task1, String task2, TaskDependency newTaskDependency) {
		TaskDependency currentTaskDependency = taskDependencyRepository.findByTask1AndTask2(task1, task2);

		if (currentTaskDependency == null) {
			currentTaskDependency = new TaskDependency();
		}

		currentTaskDependency.setTask1(newTaskDependency.getTask1());
		currentTaskDependency.setTask2(newTaskDependency.getTask2());
		currentTaskDependency.setProject(newTaskDependency.getProject());

		taskDependencyRepository.save(currentTaskDependency);
	}

	public void deleteTaskDependency(String task1, String task2) {
		taskDependencyRepository.deleteByTask1AndTask2(task1, task2);
	}

}
