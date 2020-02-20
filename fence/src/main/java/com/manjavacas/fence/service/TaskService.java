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

	public Task getTask(int reference) {
		return taskRepository.findByReference(reference);
	}

	public List<Task> getTasksByProject(String project) {
		return taskRepository.findByProject(project);
	}

	public List<Task> getPendingTasks() {
		return taskRepository.findByDone(false);
	}

	public List<Task> getTasksByPriority(String level) {
		return taskRepository.findByPriority(level);
	}

	public void addTask(Task task) {
		taskRepository.insert(task);
	}

	public void updateTask(int reference, Task newTask) {
		Task currentTask = taskRepository.findByReference(reference);

		currentTask.setDescription(newTask.getDescription());
		currentTask.setDone(newTask.isDone());
		;
		currentTask.setDuration_days(newTask.getDuration_days());
		currentTask.setPriority(newTask.getPriority());
		currentTask.setProject(newTask.getProject());
		currentTask.setReference(newTask.getReference());

		taskRepository.save(newTask);
	}

	public void deleteTask(int reference) {
		taskRepository.deleteByReference(reference);
	}

}
