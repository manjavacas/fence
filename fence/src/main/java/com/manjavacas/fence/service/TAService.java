package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TA;
import com.manjavacas.fence.repository.TARepository;

@Service
public class TAService {

	@Autowired
	TARepository taRepository;

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
		return taRepository.findByTaskAndUser(task, user);
	}

	public void updateTAs(List<TA> listTA) {
		taRepository.deleteAll();
		taRepository.saveAll(listTA);
	}

	public void updateTA(String task, String user, TA ta) {
		TA currentTA = taRepository.findByTaskAndUser(task, user);

		if (currentTA == null) {
			currentTA = new TA();
		}

		currentTA.setTask(ta.getTask());
		currentTA.setUser(ta.getUser());
		currentTA.setProject(ta.getProject());
		currentTA.setWeight(ta.getWeight());

		taRepository.save(currentTA);
	}

	public void deleteTA(String task, String user) {
		taRepository.deleteByTaskAndUser(task, user);
	}
}
