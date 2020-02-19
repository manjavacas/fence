package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Project;

@Service
public interface ProjectRepository extends MongoRepository<Project, String>{

	Project findByName(String name);

	void deleteByName(String name);


}
