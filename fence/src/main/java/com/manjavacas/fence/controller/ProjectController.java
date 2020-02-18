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

import com.manjavacas.fence.model.Project;
import com.manjavacas.fence.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@RequestMapping("/Projects")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@RequestMapping("/Projects/{name}")
	public Project getProject(@PathVariable String name) {
		return projectService.getProject(name);
	}

	@PostMapping(value = "/Projects")
	public void addProject(@RequestBody Project Project) {
		projectService.addProject(Project);
	}

	@PutMapping(value = "/Projects/{name}")
	public void updateProject(@PathVariable String name, @RequestBody Project project) {
		projectService.updateProject(name, project);
	}

	@DeleteMapping("/Projects/{name}")
	public void deleteProject(@PathVariable String name) {
		projectService.deleteProject(name);
	}

}
