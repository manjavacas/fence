package com.manjavacas.fence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Communication;

@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {

	Communication findBy_id(String id);

	List<Communication> findByUser1(String dni);

	List<Communication> findByUser2(String dni);

	List<Communication> findByDate(Date date);

	void deleteBy_id(String id);

}
