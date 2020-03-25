package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.repository.CGrepository;

public class CGservice {

	@Autowired
	CGrepository cgRepository;

	public List<CG> getAllCG() {
		return cgRepository.findAll();
	}

	public void updateCG(List<CG> listCG) {
		cgRepository.deleteAll();
		cgRepository.saveAll(listCG);
	}

}