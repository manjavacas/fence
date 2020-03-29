package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

	Project findByName(String name);

	void deleteByName(String name);

}
