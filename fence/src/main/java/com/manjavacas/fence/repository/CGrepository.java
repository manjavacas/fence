package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.CG;

@Repository
public interface CGrepository extends MongoRepository<CG, String> {

	List<CG> findByUser1(String dni);

	List<CG> findByProject(String project);

}
