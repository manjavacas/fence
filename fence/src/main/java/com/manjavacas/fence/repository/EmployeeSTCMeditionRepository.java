package com.manjavacas.fence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.EmployeeSTCMedition;

@Repository
public interface EmployeeSTCMeditionRepository extends MongoRepository<EmployeeSTCMedition, String> {

	EmployeeSTCMedition findBy_id(String id);

	List<EmployeeSTCMedition> findByDni(String dni);

	List<EmployeeSTCMedition> findByDate(Date date);

	void deleteBy_id(String id);

}
