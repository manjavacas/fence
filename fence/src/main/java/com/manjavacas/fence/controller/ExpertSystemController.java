package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Recommendation;
import com.manjavacas.fence.service.ExpertSystemService;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@RestController
public class ExpertSystemController {

	private static String FILE = "";
	private static String BLOCK = "";
	
	@Autowired
	private ExpertSystemService expertSystemService;
	
	@RequestMapping("/Recommendations")
	public List<Recommendation> getRecommendations() {
		return expertSystemService.getRecommendations();
	}
	
	public void run() {
		// Load Fuzzy Inference System
		FIS fis = FIS.load(FILE);

		if (fis == null) {
			System.err.println("Error loading file!");
			return;
		}

		FunctionBlock fb = fis.getFunctionBlock(BLOCK);
		// JFuzzyChart.get().chart(fb);

		// Input
		fis.setVariable("coordination", .62);
		fis.setVariable("culturalDist", .7);
		fis.setVariable("overlap", 0.4);
		fis.setVariable("age1", 29);
		fis.setVariable("age2", 56);
		fis.setVariable("experience1", 0.9);
		fis.setVariable("experience2", 0.4);

		// Evaluate
		fis.evaluate();

		// Result
		// Variable risk = fb.getVariable("risk");
		// JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);

		for (int i = 1; i <= 6; i++) {
			Variable solution = fb.getVariable("solution" + i);
			if (solution.getValue() != 0) {
				System.out.println(solution.getName());
			}

		}
	}

}
