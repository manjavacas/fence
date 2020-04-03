package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.service.CommunicationService;

@RestController
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@RequestMapping("/Communications")
	public List<Communication> getAllCommunications() {
		return communicationService.getAllCommunications();
	}

	@RequestMapping("/Communications/{project}")
	public List<Communication> getCommunicationsByProject(@PathVariable String project) {
		return communicationService.getCommunicationsByProject(project);
	}

	@RequestMapping("/Communications/user1/{dni}")
	public List<Communication> getCommunicationsByUser1(@PathVariable String dni) {
		return communicationService.getCommunicationsByUser1(dni);
	}

	@RequestMapping("/Communications/user2/{dni}")
	public List<Communication> getCommunicationsByUser2(@PathVariable String dni) {
		return communicationService.getCommunicationsByUser2(dni);
	}

	@PostMapping(value = "/Communications")
	public void addCommunication(@RequestBody Communication communication) {
		communicationService.addCommunication(communication);
	}

}
