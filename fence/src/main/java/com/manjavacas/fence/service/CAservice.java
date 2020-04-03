package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.CA;
import com.manjavacas.fence.repository.CArepository;

@Service
public class CAservice {

	@Autowired
	CArepository caRepository;

	public List<CA> getAllCA() {
		return caRepository.findAll();
	}

	public void updateCA(List<CA> listCA) {
		caRepository.deleteAll();
		caRepository.saveAll(listCA);
	}

	public List<CA> getCA(String user1, String user2, String project, String task) {
		return caRepository.findCustom(user1, user2, project, task);
	}

}