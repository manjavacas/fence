package com.manjavacas.fence.service;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Communication;
import com.manjavacas.fence.model.Employee;

@Service
public class CommunicationService {

	public List<Employee> getAllCommunications() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationByStarter(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationByListener(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Communication> getCommunicationByDate(Date date) {
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
