package com.manjavacas.fence.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.EmployeeSTCMedition;

@Repository
public interface EmployeeSTCMeditionRepository extends MongoRepository<EmployeeSTCMedition, String> {

	EmployeeSTCMedition findById(ObjectId id);

	List<EmployeeSTCMedition> findByDni(String dni);

	List<EmployeeSTCMedition> findByDate(Date date);

	void deleteById(ObjectId id);

}
