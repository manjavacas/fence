package com.manjavacas.fence.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Communication;

@Service
public class CommunicationService {

	public List<Communication> getAllCommunications() {
		// TODO Auto-generated method stub
		return null;
	}

	public Communication getCommunication(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationsByStarter(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationsByListener(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationsByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCommunication(Communication communication) {
		// TODO Auto-generated method stub

	}

	public void updateCommunication(ObjectId id, Communication communication) {
		// TODO Auto-generated method stub

	}

	public void deleteCommunication(ObjectId id) {
		// TODO Auto-generated method stub

	}

}
