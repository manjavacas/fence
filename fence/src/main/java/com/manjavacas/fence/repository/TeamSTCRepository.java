package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.TeamSTC;

@Repository
public interface TeamSTCRepository extends MongoRepository<TeamSTC, String> {

	List<TeamSTC> findByTeam(String name);

}