package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manjavacas.fence.model.CG;

public interface CGrepository extends MongoRepository<CG, String> {

}
