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
		return communicationRepository.findBy_id(id.toString());
	}

	public List<Communication> getCommunicationsByUser1(String dni) {
		return communicationRepository.findByUser1(dni);
	}

	public List<Communication> getCommunicationsByUser2(String dni) {
		return communicationRepository.findByUser2(dni);
	}

	public List<Communication> getCommunicationsByDate(Date date) {
		return communicationRepository.findByDate(date);
	}

	public void addCommunication(Communication communication) {
		communicationRepository.insert(communication);
	}

	public void updateCommunication(ObjectId id, Communication newCommunication) {
		Communication currentCommunication = communicationRepository.findBy_id(id.toString());

		if (currentCommunication == null) {
			currentCommunication = new Communication();
		}

		currentCommunication.setUser1(newCommunication.getUser1());
		currentCommunication.setUser2(newCommunication.getUser2());
		currentCommunication.setDate(newCommunication.getDate());
		currentCommunication.setQuality(newCommunication.getQuality());
		currentCommunication.setDuration(newCommunication.getDuration());

		communicationRepository.save(currentCommunication);
	}

	public void deleteCommunication(ObjectId id) {
		communicationRepository.deleteBy_id(id.toString());
	}

}
