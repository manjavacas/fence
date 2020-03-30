package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TaskDependency;
import com.manjavacas.fence.repository.TaskDependencyRepository;

@Service
public class TaskDependencyService {

	@Autowired
	private TaskDependencyRepository taskDependencyRepository;

	public List<TaskDependency> getAllTaskDependencies() {
		return taskDependencyRepository.findAll();
	}

	public List<TaskDependency> getTaskDependenciesOf(String reference) {
		return taskDependencyRepository.findByTask1(reference);
	}

	public void updateTaskDependency(String reference, TaskDependency newTask) {
		TaskDependency currentTaskDependency = taskDependencyRepository.findByReference(reference);

		if (currentTaskDependency == null) {
			currentTaskDependency = new TaskDependency();
		}

		currentTaskDependency.setReference(newTask.getReference());
		currentTaskDependency.setTask1(newTask.getTask1());
		currentTaskDependency.setTask2(newTask.getTask2());
		currentTaskDependency.setProject(newTask.getProject());
		currentTaskDependency.setWeight(newTask.getWeight());

		taskDependencyRepository.save(currentTaskDependency);
	}

	public void deleteTaskDependency(String reference) {
		taskDependencyRepository.deleteByReference(reference);
	}
}
