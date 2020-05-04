package com.manjavacas.fence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.MinGap;

@RestController
public class MinGapController {
	
	@Autowired 
	private MinGapController minGapController;

	@RequestMapping("/MinGap/{user1}/{user2}/{project}")
	public MinGap getMinGap(@PathVariable("user1") String user1, @PathVariable("user2") String user2, @PathVariable("project") String project) {
		return minGapController.getMinGap(user1, user2, project);
	}
	
	@RequestMapping("/MinGap/{user1}/{user2}/{project}/weight")
	public double getMinGapWeight(@PathVariable("user1") String user1, @PathVariable("user2") String user2, @PathVariable("project") String project) {
		return minGapController.getMinGapWeight(user1, user2, project);
	}
	
	@PutMapping(value = "/MinGap/{user1}/{user2}/{project}")
	public void updateMinGap(@PathVariable("user1") String user1, @PathVariable("user2") String user2, @PathVariable("project") String project, @RequestBody MinGap minGap) {
		minGapController.updateMinGap(user1, user2, project, minGap);
	}
	
}
