package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.CA;

@Repository
public interface CArepository extends MongoRepository<CA, String> {

	@Query("{ 'user1' : ?0, 'user2' : ?1, 'project' : ?3, 'task' : ?4}")
	List<CA> findCustom(String user1, String user2, String project, String task);

}