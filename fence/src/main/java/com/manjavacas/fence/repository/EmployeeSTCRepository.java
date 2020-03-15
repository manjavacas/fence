package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.EmployeeSTC;

@Repository
public interface EmployeeSTCRepository extends MongoRepository<EmployeeSTC, String> {

	List<EmployeeSTC> findByEmployee(String employee);

}
