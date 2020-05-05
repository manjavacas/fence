package com.manjavacas.fence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.MinGap;
import com.manjavacas.fence.service.MinGapService;

@RestController
public class MinGapController {

	private final static String DEFAULT_USER1 = "fenceapp";
	private final static String DEFAULT_USER2 = "fenceapp";
	private final static String DEFAULT_PROJECT = "fence";

	@Autowired
	private MinGapService minGapService;

	@RequestMapping("/MinGap/{user1}/{user2}/{project}")
	public MinGap getMinGap(@PathVariable("user1") String user1, @PathVariable("user2") String user2,
			@PathVariable("project") String project) {
		return minGapService.getMinGap(user1, user2, project);
	}

	@RequestMapping("/MinGap/{user1}/{user2}/{project}/weight")
	public double getMinGapWeight(@PathVariable("user1") String user1, @PathVariable("user2") String user2,
			@PathVariable("project") String project) {
		return minGapService.getMinGapWeight(user1, user2, project);
	}

	@RequestMapping("/MinGap/default")
	public double getDefaultMinGapWeight() {
		return minGapService.getMinGapWeight(DEFAULT_USER1, DEFAULT_USER2, DEFAULT_PROJECT);
	}
	
	@RequestMapping("/MinGap/reset/{project}")
	public void resetMinGapsProject(@PathVariable String project) {
		minGapService.resetMinGapsProject(project);
	}
	
	@PutMapping(value = "/MinGap/default")
	public void updateDefaultMinGap(@RequestBody MinGap minGap) {
		minGapService.updateMinGap(DEFAULT_USER1, DEFAULT_USER2, DEFAULT_PROJECT, minGap);
	}

	@PutMapping(value = "/MinGap/{user1}/{user2}/{project}")
	public void updateMinGap(@PathVariable("user1") String user1, @PathVariable("user2") String user2,
			@PathVariable("project") String project, @RequestBody MinGap minGap) {
		minGapService.updateMinGap(user1, user2, project, minGap);
	}

}
