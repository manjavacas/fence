package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Recommendation;
import com.manjavacas.fence.repository.RecommendationRepository;

@Service
public class RecommendationService {

	@Autowired
	private RecommendationRepository recommendationRepository;

	public List<Recommendation> getAllRecommendations() {
		return recommendationRepository.findAll();
	}

	public void updateRecommendations(List<Recommendation> listRecommendations) {
		recommendationRepository.deleteAll();
		recommendationRepository.saveAll(listRecommendations);
	}

}
