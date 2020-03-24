package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.TD;
import com.manjavacas.fence.repository.TDrepository;

@Service
public class TDservice {

	@Autowired
	TDrepository tdRepository;

	public List<TD> getAllTD() {
		return tdRepository.findAll();
	}

	public TD getTDByTask1AndTask2(String task1, String task2) {
		List<TD> result = tdRepository.findByTask1AndTask2(task1, task2);
		
		if (result.size() == 0) {
			result = tdRepository.findByTask1AndTask2(task2, task1);
		}
		
		return result.get(0);
	}

	public void updateTD(List<TD> listTD) {
		tdRepository.deleteAll();
		tdRepository.saveAll(listTD);
	}

}
