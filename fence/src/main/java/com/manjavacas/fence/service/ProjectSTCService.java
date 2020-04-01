package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.ProjectSTC;
import com.manjavacas.fence.repository.ProjectSTCRepository;

@Service
public class ProjectSTCService {

	@Autowired
	private ProjectSTCRepository projectSTCRepository;

	public List<ProjectSTC> getAllProjectSTCs() {
		return projectSTCRepository.findAll();
	}

	public List<ProjectSTC> getProjectSTC(String project) {
		return projectSTCRepository.findByProject(project);
	}

	public void addProjectSTC(ProjectSTC projectSTC) {
		projectSTCRepository.insert(projectSTC);
	}

	public ProjectSTC getLatestMedition(String project) {
		List<ProjectSTC> allSTCMeditions = projectSTCRepository.findByProject(project);

		ProjectSTC latest = null;
		for (ProjectSTC projectSTC : allSTCMeditions) {
			if (latest == null) {
				latest = projectSTC;
			} else if (projectSTC.getDate().compareTo(latest.getDate()) > 0) {
				latest = projectSTC;
			}
		}

		return latest;
	}

}
