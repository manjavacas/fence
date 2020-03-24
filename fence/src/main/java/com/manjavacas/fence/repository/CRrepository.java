package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manjavacas.fence.model.CR;

public interface CRrepository extends MongoRepository<CR, String> {

}
