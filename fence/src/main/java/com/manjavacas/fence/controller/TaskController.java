package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	@RequestMapping("/Tasks")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@RequestMapping("/Task/{ref}")
	public Task getTask(@PathVariable int ref) {
		return taskService.getTask(ref);
	}

	@RequestMapping("/Task/project/{project}")
	public Task getTasksByProject(@PathVariable String project) {
		return taskService.getTasksByProject(project);
	}

	@RequestMapping("/Task/pending")
	public List<Task> getPendingTasks() {
		return taskService.getPendingTasks();
	}

	@RequestMapping("/Task/priority/{level}")
	public List<Task> getTasksByPriority(@PathVariable String level) {
		return taskService.getTasksByPriority(level);
	}

	@PostMapping(value = "/Tasks")
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task);
	}

	@PutMapping(value = "/Tasks/{ref}")
	public void updateTask(@PathVariable int ref, @RequestBody Task task) {
		taskService.updateTask(ref, task);
	}

	@DeleteMapping("/Tasks/{ref}")
	public void deleteTask(@PathVariable int ref) {
		taskService.deleteTask(ref);
	}

}
