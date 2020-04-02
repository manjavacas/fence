package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.repository.TDRepository;

@Service
public class TDService {

	@Autowired
	TDRepository tdRepository;

	public List<TD> getAllTD() {
		return tdRepository.findAll();
	}

	public List<TD> getTDByTask(String task) {
		return tdRepository.findByTask1(task);
	}

	public TD getTDByTask1AndTask2(String task1, String task2) {
		return tdRepository.findByTask1AndTask2(task1, task2);
	}

	public List<TD> getTDByProject(String project) {
		return tdRepository.findByProject(project);
	}

	public void updateTD(String task1, String task2, TD td) {
		TD currentTD = tdRepository.findByTask1AndTask2(task1, task2);

		if (currentTD == null) {
			currentTD = new TD();
		}

		currentTD.setTask1(td.getTask1());
		currentTD.setTask2(td.getTask2());
		currentTD.setProject(td.getProject());
		currentTD.setWeight(td.getWeight());

		tdRepository.save(currentTD);
	}

	public void updateTDs(List<TD> listTD) {
		tdRepository.deleteAll();
		tdRepository.saveAll(listTD);
	}

	public void deleteTD(String task1, String task2) {
		tdRepository.deleteByTask1AndTask2(task1, task2);
	}

}
