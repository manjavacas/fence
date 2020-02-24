package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findByDni(String dni);

	List<Employee> findByTeam(String team);

	List<Employee> deleteByDni(String dni);

	Employee findBy_id(String string);

}
