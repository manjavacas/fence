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

import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.service.CommunicationService;

@RestController
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@RequestMapping("/Communications")
	public List<Employee> getAllCommunications() {
		return communicationService.getAllCommunications();
	}

	@RequestMapping("/Communications/starter/{dni}")
	public List<Communication> getCommunicationsByStarter(@PathVariable String dni) {
		return communicationService.getCommunicationByStarter(dni);
	}

	@RequestMapping("/Communications/listener/{dni}")
	public List<Communication> getCommunicationsByListener(@PathVariable String dni) {
		return communicationService.getCommunicationByListener(dni);
	}

	@RequestMapping("/Communications/{dateStr}")
	public List<Communication> getCommunicationsByDate(@PathVariable String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		return communicationService.getCommunicationByDate(date);
	}

	@PostMapping(value = "/Communications")
	public void addCommunication(@RequestBody Communication communication) {
		communicationService.addCommunication(communication);
	}

	@PutMapping(value = "/Communications/{id}")
	public void updateCommunication(@PathVariable ObjectId id, @RequestBody Communication communication) {
		communicationService.updateCommunication(id, communication);
	}

	@DeleteMapping("/Communications/{id}")
	public void deleteCommunication(@PathVariable ObjectId id) {
		communicationService.deleteCommunication(id);
	}
}
