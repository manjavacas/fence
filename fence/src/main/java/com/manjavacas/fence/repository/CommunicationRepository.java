package com.manjavacas.fence.repository;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Communication;

@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {

	Communication findById(ObjectId id);

	List<Communication> findByStarter(String dni);

	List<Communication> findByListener(String dni);

	List<Communication> findByDate(Date date);

	void deleteById(ObjectId id);

}
