package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task getTask(String reference) {
		return taskRepository.findByReference(reference);
	}

	public List<Task> getTasksByProject(String project) {
		return taskRepository.findByProject(project);
	}

	public List<Task> getPendingTasksByProject(String project) {
		return taskRepository.findPendingTasksInProject(project);
	}

	public List<Task> getTasksByPriority(String level) {
		return taskRepository.findByPriority(level);
	}

	public void updateTask(String reference, Task newTask) {
		Task currentTask = taskRepository.findByReference(reference);

		if (currentTask == null) {
			currentTask = new Task();
		}

		currentTask.setReference(newTask.getReference());
		currentTask.setDescription(newTask.getDescription());
		currentTask.setDuration_days(newTask.getDuration_days());
		currentTask.setPriority(newTask.getPriority());
		currentTask.setDone(newTask.isDone());
		currentTask.setProject(newTask.getProject());

		taskRepository.save(currentTask);
	}

	public void deleteTask(String reference) {
		taskRepository.deleteByReference(reference);
	}

}
