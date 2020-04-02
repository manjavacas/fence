package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.service.TDService;

@RestController
public class TDController {

	@Autowired
	private TDService tdService;

	@RequestMapping("/TD")
	public List<TD> getAllTD() {
		return tdService.getAllTD();
	}

	@RequestMapping("/TD/{task}")
	public List<TD> getDependenciesOf(@PathVariable String task) {
		return tdService.getTDByTask(task);
	}

	@RequestMapping("/TD/{task1}/{task2}")
	public TD getTD(@PathVariable String task1, String task2) {
		return tdService.getTDByTask1AndTask2(task1, task2);
	}

	@RequestMapping("/TD/project/{project}")
	public List<TD> getTDByProject(@PathVariable String project) {
		return tdService.getTDByProject(project);
	}

	@PutMapping(value = "/TD/{task1}/{task2}")
	public void updateTask(@PathVariable String task1, String task2, @RequestBody TD td) {
		tdService.updateTD(task1, task2, td);
	}

	@DeleteMapping("/TD/{task1}/{task2}")
	public void deleteTask(@PathVariable String task1, String task2) {
		tdService.deleteTD(task1, task2);
	}
}
