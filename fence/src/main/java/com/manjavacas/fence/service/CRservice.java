package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.CR;
import com.manjavacas.fence.repository.CRrepository;

@Service
public class CRservice {

	@Autowired
	CRrepository crRepository;

	public List<CR> getAllCR() {
		return crRepository.findAll();
	}
	
	public List<CR> getCRByUser1(String dni) {
		return crRepository.findByUser1(dni);
	}

	public void updateCR(List<CR> listCR) {
		crRepository.deleteAll();
		crRepository.saveAll(listCR);
	}
	
}
