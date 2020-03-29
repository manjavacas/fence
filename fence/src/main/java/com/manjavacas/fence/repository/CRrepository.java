package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.CR;

@Repository
public interface CRrepository extends MongoRepository<CR, String> {

	List<CR> findByUser1(String dni);

}
