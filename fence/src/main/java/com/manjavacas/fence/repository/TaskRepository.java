package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	Task findByRef(int ref);

	List<Task> findByProject(String project);

	List<Task> findPendingTasks();

	List<Task> findByPriority(String level);

	void deleteByRef(int ref);

}
