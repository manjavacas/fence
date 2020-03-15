package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	Task findByReference(String reference);

	List<Task> findByProject(String project);

	List<Task> findByDone(boolean done);

	List<Task> findByPriority(String level);

	List<Task> findByType(String type);

	void deleteByReference(String reference);

}
