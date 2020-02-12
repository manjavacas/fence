package com.manjavacas.fence.employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	@Query("{ '_id' : ?0 }")
	Employee findBy_Id(String _id);
}
