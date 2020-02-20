package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Admin;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

	Admin findByUsername(String username);

	void deleteByUsername(String username);

}
