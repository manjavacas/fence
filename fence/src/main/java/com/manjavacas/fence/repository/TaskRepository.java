package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

	Task findByReference(String reference);

	List<Task> findByProject(String project);

	@Query("{ 'project' : ?0 , 'done' : false}")
	List<Task> findPendingTasksInProject(String project);

	List<Task> findByPriority(String level);

	void deleteByReference(String reference);

}
