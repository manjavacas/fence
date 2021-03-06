package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Communication;

@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {

	List<Communication> findByUser1(String dni);

	List<Communication> findByUser2(String dni);
	
	List<Communication> findByUser1AndUser2(String dni1, String dni2);

	List<Communication> findByUser1OrUser2(String dni1, String dni2);

	List<Communication> findByProject(String project);

}
