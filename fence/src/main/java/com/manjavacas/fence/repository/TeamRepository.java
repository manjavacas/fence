package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Team;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {

	Team findByName(String name);

	List<Team> findByLocation(String location);

	List<Team> findByProject(String project);

	void add(Team team);

	void deleteByName(String name);

}
