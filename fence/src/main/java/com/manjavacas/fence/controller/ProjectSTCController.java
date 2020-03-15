package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.ProjectSTC;
import com.manjavacas.fence.service.ProjectSTCService;

@RestController
public class ProjectSTCController {

	@Autowired
	private ProjectSTCService projectSTCService;

	@RequestMapping("/ProjectsSTC")
	public List<ProjectSTC> getAllProjectSTC() {
		return projectSTCService.getAllProjectSTCs();
	}

	@RequestMapping("/ProjectsSTC/{project}")
	public List<ProjectSTC> getProjectSTC(@PathVariable String project) {
		return projectSTCService.getProjectSTC(project);
	}

	@PostMapping(value = "/ProjectsSTC")
	public void addProjectSTC(@RequestBody ProjectSTC projectSTC) {
		projectSTCService.addProjectSTC(projectSTC);
	}

}
