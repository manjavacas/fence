package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Team;
import com.manjavacas.fence.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	public Team getTeam(String name) {
		return teamRepository.findByName(name);
	}

	public List<Team> getTeamsByLocation(String location) {
		return teamRepository.findByLocation(location);
	}

	public List<Team> getTeamsByProject(String project) {
		return teamRepository.findByProject(project);
	}

	public void addTeam(Team team) {
		teamRepository.insert(team);
	}

	public void updateTeam(String name, Team newTeam) {
		Team currentTeam = teamRepository.findByName(name);

		if (currentTeam == null) {
			currentTeam = new Team();
		}

		currentTeam.setName(newTeam.getName());
		currentTeam.setLocation(newTeam.getLocation());
		currentTeam.setProject(newTeam.getProject());

		teamRepository.save(currentTeam);
	}

	public void deleteTeam(String name) {
		teamRepository.deleteByName(name);
	}

}
