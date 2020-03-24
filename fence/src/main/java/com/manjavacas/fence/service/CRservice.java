package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manjavacas.fence.model.CR;
import com.manjavacas.fence.repository.CRrepository;

public class CRservice {

	@Autowired
	CRrepository crRepository;

	public List<CR> getAllCR() {
		return crRepository.findAll();
	}

	public void updateCR(List<CR> listCR) {
		crRepository.deleteAll();
		crRepository.saveAll(listCR);
	}
	
}
