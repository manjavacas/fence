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
import com.manjavacas.fence.service.CommunicationService;

@RestController
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@RequestMapping("/Communications")
	public List<Communication> getAllCommunications() {
		return communicationService.getAllCommunications();
	}
	
	@RequestMapping("/Communications/{id}")
	public Communication getCommunication(@PathVariable ObjectId id) {
		return communicationService.getCommunication(id);
	}

	@RequestMapping("/Communications/user1/{dni}")
	public List<Communication> getCommunicationsByUser1(@PathVariable String dni) {
		return communicationService.getCommunicationsByUser1(dni);
	}

	@RequestMapping("/Communications/user2/{dni}")
	public List<Communication> getCommunicationsByUser2(@PathVariable String dni) {
		return communicationService.getCommunicationsByUser2(dni);
	}

	@RequestMapping("/Communications/{dateStr}")
	public List<Communication> getCommunicationsByDate(@PathVariable String dateStr) throws ParseException {
		Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		return communicationService.getCommunicationsByDate(date);
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
