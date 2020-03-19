package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manjavacas.fence.model.TaskDependency;

public interface TaskDependencyRepository extends MongoRepository<TaskDependency, String> {

	List<TaskDependency> findByTask1(String ref);

}
