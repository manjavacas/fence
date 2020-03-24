package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manjavacas.fence.model.TD;

public interface TDrepository extends MongoRepository<TD, String>{

	List<TD> findByTask1AndTask2(String task1, String task2);

}
