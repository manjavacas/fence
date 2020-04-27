package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Recommendation;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

	List<Recommendation> findByProject(String project);

}
