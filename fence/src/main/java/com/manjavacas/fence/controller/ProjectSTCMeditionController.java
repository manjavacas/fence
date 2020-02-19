package com.manjavacas.fence.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.ProjectSTCMedition;
import com.manjavacas.fence.service.ProjectSTCMeditionService;

@RestController
public class ProjectSTCMeditionController {

	@Autowired
	private ProjectSTCMeditionService projectSTCMeditionService;

	@RequestMapping("/ProjectSTCMeditions")
	public List<ProjectSTCMedition> getAllProjectSTCMeditions() {
		return projectSTCMeditionService.getAllProjectSTCMeditions();
	}

	@RequestMapping("/ProjectSTCMeditions/{id}")
	public ProjectSTCMedition getProjectSTCMedition(@PathVariable ObjectId id) {
		return projectSTCMeditionService.getProjectSTCMedition(id);
	}

	@RequestMapping("/ProjectSTCMeditions/project/{dni}")
	public List<ProjectSTCMedition> getProjectSTCMeditionsByDni(@PathVariable String dni) {
		return projectSTCMeditionService.getProjectSTCMeditionsByDni(dni);
	}

	@RequestMapping("/ProjectSTCMeditions/date/{dateStr}")
	public List<ProjectSTCMedition> getProjectSTCMeditionsByDate(@PathVariable String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		return projectSTCMeditionService.getProjectSTCMeditionsByDate(date);
	}

	@PostMapping(value = "/ProjectSTCMeditions")
	public void addProjectSTCMedition(@RequestBody ProjectSTCMedition ProjectSTCMedition) {
		projectSTCMeditionService.addProjectSTCMedition(ProjectSTCMedition);
	}

	@PutMapping(value = "/ProjectSTCMedition/{id}")
	public void updateProjectSTCMedition(@PathVariable ObjectId id,
			@RequestBody ProjectSTCMedition projectSTCMedition) {
		projectSTCMeditionService.updateProjectSTCMedition(id, projectSTCMedition);
	}

	@DeleteMapping("/ProjectSTCMeditions/{id}")
	public void deleteProjectSTCMedition(@PathVariable ObjectId id) {
		projectSTCMeditionService.deleteProjectSTCMedition(id);
	}
}
