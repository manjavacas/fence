package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Project;
import com.manjavacas.fence.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project getProject(String name) {
		return projectRepository.findByName(name);
	}

	public void updateProject(String name, Project newProject) {
		Project currentProject = projectRepository.findByName(name);

		if (currentProject == null) {
			currentProject = new Project();
		}

		currentProject.setName(newProject.getName());
		currentProject.setDescription(newProject.getDescription());
		currentProject.setStart_date(newProject.getStart_date());
		currentProject.setEnd_date(newProject.getEnd_date());
		currentProject.setTeams(newProject.getTeams());

		projectRepository.save(currentProject);
	}

	public void deleteProject(String name) {
		projectRepository.deleteByName(name);
	}

}
