package com.manjavacas.fence.employee;

import java.util.List;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	@Query("{ 'dni' : ?0 }")
	Employee findByDni(String dni);

	@DeleteQuery("{ 'dni' : ?0}")
	List<Employee> deleteByDni(String dni);

	@Query("{ 'team' : ?0 }")
	List<Employee> findByTeam(String team);

}
