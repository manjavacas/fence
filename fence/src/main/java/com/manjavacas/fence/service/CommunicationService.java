package com.manjavacas.fence.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
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

	public Communication getCommunication(ObjectId id) {
		return communicationRepository.findById(id);
	}

	public List<Communication> getCommunicationsByStarter(String dni) {
		return communicationRepository.findByStarter(dni);
	}

	public List<Communication> getCommunicationsByListener(String dni) {
		return communicationRepository.findByListener(dni);
	}

	public List<Communication> getCommunicationsByDate(Date date) {
		return communicationRepository.findByDate(date);
	}

	public void addCommunication(Communication communication) {
		communicationRepository.insert(communication);
	}

	public void updateCommunication(ObjectId id, Communication newCommunication) {
		Communication currentCommunication = communicationRepository.findById(id);

		currentCommunication.setListener(newCommunication.getListener());
		currentCommunication.setQuality(newCommunication.getQuality());
		currentCommunication.setStarter(newCommunication.getStarter());
		currentCommunication.setTime_end(newCommunication.getTime_end());
		currentCommunication.setTime_start(newCommunication.getTime_start());

		communicationRepository.save(currentCommunication);
	}

	public void deleteCommunication(ObjectId id) {
		communicationRepository.deleteById(id);
	}

}
