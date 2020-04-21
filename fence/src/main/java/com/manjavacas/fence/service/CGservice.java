package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.repository.CGrepository;

@Service
public class CGservice {

	@Autowired
	CGrepository cgRepository;

	public List<CG> getAllCG() {
		return cgRepository.findAll();
	}

	public List<CG> getCGByUser1(String dni) {
		return cgRepository.findByUser1(dni);
	}
	
	public List<CG> getCGByProject(String project) {
		return cgRepository.findByProject(project);
	}

	public void updateCG(List<CG> listCG) {
		cgRepository.deleteAll();
		cgRepository.saveAll(listCG);
	}

}