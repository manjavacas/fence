package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.TaskAssignment;

@Repository
public interface TaskAssignmentRepository extends MongoRepository<TaskAssignment, String> {

	TaskAssignment findByTaskAndUser(String task, String user);

	List<TaskAssignment> findByTask(String task);

	List<TaskAssignment> findByUser(String user);

	void deleteByTaskAndUser(String task, String user);

}
