package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findByDni(String dni);

	void deleteByDni(String dni);



}
