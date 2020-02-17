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

import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamService teamService;

	@RequestMapping("/Teams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@RequestMapping("/Teams/{name}")
	public Team getTeam(@PathVariable String name) {
		return teamService.getTeam(name);
	}

	@RequestMapping("/Teams/location/{location}")
	public List<Team> getTeamsByLocation(@PathVariable String location) {
		return teamService.getTeamsByLocation(location);
	}

	@RequestMapping("/Teams/project/{project}")
	public List<Team> getTeamsByProject(@PathVariable String project) {
		return teamService.getTeamsByProject(project);
	}

	@PostMapping(value = "/Teams")
	public void addTeam(@RequestBody Team team) {
		teamService.addTeam(team);
	}

	@PutMapping(value = "/Teams/{id}")
	public void updateTeam(@PathVariable String id, @RequestBody Team team) {
		teamService.updateTeam(id, team);
	}

	@DeleteMapping("/Teams/{dni}")
	public void deleteTeam(@PathVariable String name) {
		teamService.deleteTeam(name);
	}
}
