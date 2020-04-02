package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.TaskAssignment;
import com.manjavacas.fence.service.TaskAssignmentService;

@RestController
public class TaskAssignmentController {

	@Autowired
	private TaskAssignmentService taskAssignmentService;

	@RequestMapping("/TaskAssignments")
	public List<TaskAssignment> getAllTaskAssignments() {
		return taskAssignmentService.getAllTaskAssignments();
	}

	@RequestMapping("/TaskAssignments/{task}/{user}")
	public TaskAssignment getTaskAssignment(@PathVariable String task, String user) {
		return taskAssignmentService.getTaskAssignment(task, user);
	}

	@PutMapping(value = "/TaskAssignments/{task}/{user}")
	public void updateTaskAssignment(@PathVariable String task, String user,
			@RequestBody TaskAssignment taskAssignment) {
		taskAssignmentService.updateTaskAssignment(task, user, taskAssignment);
	}

	@DeleteMapping("/TaskAssignments/{task}/{user}")
	public void deleteTaskAssignment(@PathVariable String task, String user) {
		taskAssignmentService.deleteTaskAssignment(task, user);
	}

}
