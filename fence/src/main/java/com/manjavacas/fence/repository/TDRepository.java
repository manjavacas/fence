package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.TD;

@Repository
public interface TDRepository extends MongoRepository<TD, String>{

	List<TD> findByTask1(String task);
	
	TD findByTask1AndTask2(String task1, String task2);

	List<TD> findByProject(String project);

	void deleteByTask1AndTask2(String task1, String task2);

}
