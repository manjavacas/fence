package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.MinGap;
import com.manjavacas.fence.repository.MinGapRepository;

@Service
public class MinGapService {

	private final static String DEFAULT_USER1 = "fenceapp";
	private final static String DEFAULT_USER2 = "fenceapp";
	private final static String DEFAULT_PROJECT = "fence";

	@Autowired
	private MinGapRepository minGapRepository;

	public MinGap getMinGap(String user1, String user2, String project) {
		MinGap minGap = minGapRepository.findByUser1AndUser2AndProject(user1, user2, project);

		if (minGap == null) {
			// Try finding changing the order
			minGap = minGapRepository.findByUser1AndUser2AndProject(user2, user1, project);
			if (minGap == null) {
				// Else generates a default MinGap
				double defaultWeight = minGapRepository
						.findByUser1AndUser2AndProject(DEFAULT_USER1, DEFAULT_USER2, DEFAULT_PROJECT).getCustomWeight();
				minGap = new MinGap(user1, user2, project, defaultWeight);
			}
		}

		return minGap;
	}

	public double getMinGapWeight(String user1, String user2, String project) {
		return getMinGap(user1, user2, project).getCustomWeight();
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

	public void resetMinGapsProject(String project) {

		List<MinGap> minGapsProject = minGapRepository.findByProject(project);
		double defaultWeight = minGapRepository
				.findByUser1AndUser2AndProject(DEFAULT_USER1, DEFAULT_USER2, DEFAULT_PROJECT).getCustomWeight();
		for (MinGap minGap : minGapsProject) {
			minGap.setCustomWeight(defaultWeight);
			minGapRepository.save(minGap);
		}

	}

}
