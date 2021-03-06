package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.TaskDependency;

@Repository
public interface TaskDependencyRepository extends MongoRepository<TaskDependency, String> {

	TaskDependency findByTask1AndTask2(String task1, String task2);

	List<TaskDependency> findByTask1(String task);

	void deleteByTask1AndTask2(String task1, String task2);

}
