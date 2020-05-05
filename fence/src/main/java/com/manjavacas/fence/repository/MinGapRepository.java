package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.MinGap;

@Repository
public interface MinGapRepository extends MongoRepository<MinGap, String> {

	MinGap findByUser1AndUser2AndProject(String user1, String user2, String project);

	List<MinGap> findByProject(String project);

}
