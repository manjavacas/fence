package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.model.TeamSTC;
import com.manjavacas.fence.service.TeamSTCService;
import com.manjavacas.fence.service.TeamService;

@RestController
public class TeamSTCController {

	@Autowired
	private TeamSTCService teamSTCService;

	@Autowired
	private TeamService teamService;

	@RequestMapping("/TeamsSTC")
	public List<TeamSTC> getAllTeamSTC() {
		return teamSTCService.getAllTeamSTC();
	}

	@RequestMapping("/TeamsSTC/{team}")
	public List<TeamSTC> getTeamSTC(@PathVariable String team) {
		return teamSTCService.getTeamSTC(team);
	}

	@RequestMapping("/TeamsSTC/project/{project}")
	public List<TeamSTC> getTeamSTCByProject(@PathVariable String project) {

		List<Team> teamsInProject = teamService.getTeamsByProject(project);

		// Get latest STC meditions for every team in project
		List<TeamSTC> projectTeamsSTC = new ArrayList<TeamSTC>();
		for (Team team : teamsInProject) {
			projectTeamsSTC.add(teamSTCService.getLatestMedition(team.getName()));
		}

		return projectTeamsSTC;
	}

	@PostMapping(value = "/TeamsSTC")
	public void addTeamSTC(@RequestBody TeamSTC teamSTC) {
		teamSTCService.addTeamSTC(teamSTC);
	}

}