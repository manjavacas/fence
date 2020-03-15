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

	public List<Communication> getCommunicationsByUser1(String dni) {
		return communicationRepository.findByUser1(dni);
	}

	public List<Communication> getCommunicationsByUser2(String dni) {
		return communicationRepository.findByUser2(dni);
	}

	public void addCommunication(Communication communication) {
		communicationRepository.insert(communication);
	}

}
