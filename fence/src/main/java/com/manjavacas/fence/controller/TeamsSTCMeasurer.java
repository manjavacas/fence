package com.manjavacas.fence.controller;

import java.util.Hashtable;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.manjavacas.fence.model.Team;

public class TeamsSTCMeasurer {
	
	@PutMapping(value = "/STC/{project}/teams")
	public Hashtable<Team, Double> getProjectTotalSTC(@PathVariable String project) {
		return null;
	}

	@PutMapping(value = "/STC/{project}")
	public double getProjectSTC(@PathVariable String project) {
		return 0;
	}
}
