package com.manjavacas.fence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.MinGap;
import com.manjavacas.fence.repository.MinGapRepository;

@Service
public class MinGapService {
	
	private final static double DEFAULT_MIN_GAP_WEIGHT = .4;

	@Autowired
	private MinGapRepository minGapRepository;

	public MinGap getMinGap(String user1, String user2, String project) {
		return minGapRepository.findByUser1AndUser2AndProject(user1, user2, project);
	}
	
	public double getMinGapWeight(String user1, String user2, String project) {
		MinGap found = minGapRepository.findByUser1AndUser2AndProject(user1, user2, project);	
		if(found == null) {
			return DEFAULT_MIN_GAP_WEIGHT;
		} else {
			return found.getCustomWeight();
		}
	}

	public void updateMinGap(String user1, String user2, String project, MinGap newMinGap) {
		MinGap currentMinGap = minGapRepository.findByUser1AndUser2AndProject(user1, user2, project);

		if (currentMinGap == null) {
			currentMinGap = new MinGap();
		}

		currentMinGap.setUser1(newMinGap.getUser1());
		currentMinGap.setUser2(newMinGap.getUser2());
		currentMinGap.setProject(newMinGap.getProject());
		currentMinGap.setCustomWeight(newMinGap.getCustomWeight());

		minGapRepository.save(currentMinGap);

	}

}
