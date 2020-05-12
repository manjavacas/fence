package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.repository.CommunicationRepository;

@Service
public class CommunicationService {

	@Autowired
	CommunicationRepository communicationRepository;

	public List<Communication> getAllCommunications() {
		return communicationRepository.findAll();
	}

	public List<Communication> getCommunicationsByProject(String project) {
		return communicationRepository.findByProject(project);
	}

	public List<Communication> getCommunicationsByUser1(String dni) {
		return communicationRepository.findByUser1(dni);
	}

	public List<Communication> getCommunicationsByUser2(String dni) {
		return communicationRepository.findByUser2(dni);
	}

	public List<Communication> getCommunicationsByUser1OrUser2(String dni) {
		return communicationRepository.findByUser1OrUser2(dni, dni);
	}

	public List<Communication> getCommunicationsBetween(String dni1, String dni2) {
		List<Communication> communications = communicationRepository.findByUser1AndUser2(dni1, dni2);
		if (communications.size() == 0) {
			return communicationRepository.findByUser1AndUser2(dni2, dni1);
		} else {
			return communications;
		}
	}

	public void addCommunication(Communication communication) {
		communicationRepository.insert(communication);
	}

	public void deleteCommunications() {
		communicationRepository.deleteAll();
	}

}
