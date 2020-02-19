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

	public Task getTask(int ref) {
		return taskRepository.findByRef(ref);
	}

	public List<Task> getTasksByProject(String project) {
		return taskRepository.findByProject(project);
	}

	public List<Task> getPendingTasks() {
		return taskRepository.findPendingTasks();
	}

	public List<Task> getTasksByPriority(String level) {
		return taskRepository.findByPriority(level);
	}

	public void addTask(Task task) {
		taskRepository.insert(task);
	}

	public void updateTask(int ref, Task newTask) {
		Task currentTask = taskRepository.findByRef(ref);

		currentTask.setDescription(newTask.getDescription());
		currentTask.setDone(newTask.isDone());
		;
		currentTask.setDuration_days(newTask.getDuration_days());
		currentTask.setPriority(newTask.getPriority());
		currentTask.setProject(newTask.getProject());
		currentTask.setReference(newTask.getReference());

		taskRepository.save(newTask);
	}

	public void deleteTask(int ref) {
		taskRepository.deleteByRef(ref);
	}

}
