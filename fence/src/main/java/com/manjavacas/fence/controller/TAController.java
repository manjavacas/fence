package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.model.Task;
import com.manjavacas.fence.service.TAService;
import com.manjavacas.fence.service.TaskService;

@RestController
public class TAController {

	@Autowired
	private TAService taService;

	@Autowired
	private TaskService taskService;

	@RequestMapping("/TA")
	public List<TA> getAllTA() {
		return taService.getAllTA();
	}

	@RequestMapping("/TA/{task}/{user}")
	public TA getTA(@PathVariable String task, String user) {
		return taService.getTAByTaskAndUser(task, user);
	}

	@RequestMapping("/TA/project/{project}")
	public List<TA> getTAByProject(@PathVariable String project) {
		return taService.getTAByProject(project);
	}

	@RequestMapping("/TA/pending/{project}")
	public List<TA> getPendingTAByProject(@PathVariable String project) {

		List<TA> taProjectList = new ArrayList<TA>();

		for (TA ta : taService.getTAByProject(project)) {
			Task taTask = taskService.getTask(ta.getTask());
			if (!taTask.isDone()) {
				taProjectList.add(ta);
			}
		}

		return taProjectList;
	}

	@PutMapping(value = "/TA/{task}/{user}")
	public void updateTask(@PathVariable String task, String user, @RequestBody TA ta) {
		taService.updateTA(task, user, ta);
	}

	@DeleteMapping("/TA/{task}/{user}")
	public void deleteTask(@PathVariable String task, String user) {
		taService.deleteTA(task, user);
	}
}
