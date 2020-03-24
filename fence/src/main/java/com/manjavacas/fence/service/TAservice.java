package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.repository.TArepository;

@Service
public class TAservice {
	
	final static String COLLECTION = "";

	@Autowired
	TArepository taRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	public List<TA> getAllTA() {
		return taRepository.findAll();
	}
	
	public List<TA> getTAByProject(String project) {
		return taRepository.findByProject(project);
	}

	public List<TA> getTAByTask(String task) {
		return taRepository.findByTask(task);
	}
	
	public TA getTAByTaskAndUser(String task, String user) {
		return taRepository.findByTaskAndUser(task, user).get(0);
	}

	public void updateTA(List<TA> listTA) {
		taRepository.deleteAll();
		taRepository.saveAll(listTA);
	}
}
