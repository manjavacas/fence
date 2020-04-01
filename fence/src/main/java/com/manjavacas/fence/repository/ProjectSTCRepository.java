package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.ProjectSTC;

@Repository
public interface ProjectSTCRepository extends MongoRepository<ProjectSTC, String> {

	List<ProjectSTC> findByProject(String project);

}
