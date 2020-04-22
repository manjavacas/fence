package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Recommendation;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommendation, String> {

}
