package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TeamSTC;
import com.manjavacas.fence.repository.TeamSTCRepository;

@Service
public class TeamSTCService {

	@Autowired
	private TeamSTCRepository teamSTCRepository;

	public List<TeamSTC> getAllTeamSTC() {
		return teamSTCRepository.findAll();
	}

	public List<TeamSTC> getTeamSTC(String name) {
		return teamSTCRepository.findByTeam(name);
	}

	public void addTeamSTC(TeamSTC teamSTC) {
		teamSTCRepository.insert(teamSTC);
	}

	public TeamSTC getLatestMedition(String name) {
		List<TeamSTC> allSTCMeditions = teamSTCRepository.findByTeam(name);

		TeamSTC latest = null;
		for (TeamSTC teamSTC : allSTCMeditions) {
			if (latest == null) {
				latest = teamSTC;
			} else if (teamSTC.getDate().compareTo(latest.getDate()) > 0) {
				latest = teamSTC;
			}
		}

		return latest;
	}

}
