package com.manjavacas.fence.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.AssignedTask;
import com.manjavacas.fence.model.Employee;

@Repository
public interface AssignedTaskRepository extends MongoRepository<AssignedTask, String> {

	AssignedTask findById(ObjectId id);

	List<AssignedTask> findTasksByEmployee(String dni);

	List<Employee> findEmployeesByTask(int ref);

	void deleteById(ObjectId id);

}
