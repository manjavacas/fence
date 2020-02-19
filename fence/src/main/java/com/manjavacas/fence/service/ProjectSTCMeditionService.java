package com.manjavacas.fence.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.ProjectSTCMedition;
import com.manjavacas.fence.repository.ProjectSTCMeditionRepository;

@Service
public class ProjectSTCMeditionService {

	@Autowired
	ProjectSTCMeditionRepository projectSTCMeditionRepository;

	public List<ProjectSTCMedition> getAllProjectSTCMeditions() {
		return projectSTCMeditionRepository.findAll();
	}

	public ProjectSTCMedition getProjectSTCMedition(ObjectId id) {
		return projectSTCMeditionRepository.findById(id);
	}

	public List<ProjectSTCMedition> getProjectSTCMeditionsByDni(String dni) {
		return projectSTCMeditionRepository.findByDni(dni);
	}

	public List<ProjectSTCMedition> getProjectSTCMeditionsByDate(Date date) {
		return projectSTCMeditionRepository.findByDate(date);
	}

	public void addProjectSTCMedition(ProjectSTCMedition projectSTCMedition) {
		projectSTCMeditionRepository.insert(projectSTCMedition);
	}

	public void updateProjectSTCMedition(ObjectId id, ProjectSTCMedition newProjectSTCMedition) {
		ProjectSTCMedition currentProjectSTCMedition = projectSTCMeditionRepository.findById(id);

		currentProjectSTCMedition.setDate(newProjectSTCMedition.getDate());
		currentProjectSTCMedition.setProject(newProjectSTCMedition.getProject());
		currentProjectSTCMedition.setStc(newProjectSTCMedition.getStc());

		projectSTCMeditionRepository.save(currentProjectSTCMedition);
	}

	public void deleteProjectSTCMedition(ObjectId id) {
		projectSTCMeditionRepository.deleteById(id);
	}

}
