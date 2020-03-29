package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.TA;

@Repository
public interface TArepository extends MongoRepository<TA, String>{

	List<TA> findByTask(String task);

	List<TA> findByProject(String project);

	List<TA> findByTaskAndUser(String task, String user);

}
