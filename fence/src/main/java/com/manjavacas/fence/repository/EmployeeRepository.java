package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	@Query("{ 'dni' : ?0 }")
	Employee findByDni(String dni);

	@Query("{ 'team' : ?0 }")
	List<Employee> findByTeam(String team);

	@DeleteQuery("{ 'dni' : ?0}")
	List<Employee> deleteByDni(String dni);

	Employee findBy_id(String string);

}
