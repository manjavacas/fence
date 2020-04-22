package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Recommendation;
import com.manjavacas.fence.service.CGservice;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.RecommendationService;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@RestController
public class ExpertSystemController {

	private final static String FILE = "/fence/src/main/resources/rules/rules.fcl";
	private final static String BLOCK = "recommender";
	private final static int N_SOLUTIONS = 6;

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private CGservice cgService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/Recommendations")
	public List<Recommendation> getRecommendations() {
		return recommendationService.getAllRecommendations();
	}

	@RequestMapping("/Recommendations/calculate")
	public List<Recommendation> calculateRecommendations() {
		return runRecommender();
	}

	public List<Recommendation> runRecommender() {

		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		// Get all coordination gaps
		List<CG> gaps = cgService.getAllCG();

		// Load fuzzy inference system
		FIS fis = FIS.load(FILE);

		// Get the recommender function block
		FunctionBlock fb = fis.getFunctionBlock(BLOCK);

		// JFuzzyChart.get().chart(fb);

		// Input
		fis.setVariable("culturalDist", .7);

		for (CG gap : gaps) {

			Employee user1 = employeeService.getEmployee(gap.getUser1());
			Employee user2 = employeeService.getEmployee(gap.getUser2());

			// Set variables
			fis.setVariable("age1", user1.getAge());
			fis.setVariable("age2", user2.getAge());

			fis.setVariable("experience1", user1.getExperienceNum());
			fis.setVariable("experience2", user2.getExperienceNum());

			fis.setVariable("coordination", gap.getWeight());

			fis.setVariable("overlap", parseOverlap(user1.getTimezone(), user2.getTimezone()));
			fis.setVariable("culturalDist", parseCulturalDist(user1.getCountry(), user2.getCountry()));

			// Evaluate
			fis.evaluate();

			// Get recommended solution
			String recommended = null;
			for (int i = 1; i <= N_SOLUTIONS; i++) {
				Variable solution = fb.getVariable("solution" + i);
				// JFuzzyChart.get().chart(solution, risk.getDefuzzifier(), true);
				if (solution.getValue() != 0) {
					recommended = solution.getName();
				}
			}

			if (recommended != null) {

				// Get solution text
				String solutionText = "";
				switch (recommended) {
				case "solution1":
					solutionText += "Solution1";
					break;
				case "solution2":
					solutionText += "Solution2";
					break;
				case "solution3":
					solutionText += "Solution3";
					break;
				case "solution4":
					solutionText += "Solution4";
					break;
				case "solution5":
					solutionText += "Solution5";
					break;
				case "solution6":
					solutionText += "Solution6";
					break;
				}

				recommendations.add(new Recommendation(user1.getName(), user2.getName(), solutionText));
			}

		}

		// Update database
		recommendationService.updateRecommendations(recommendations);

		return recommendations;

	}

	private double parseCulturalDist(String country1, String country2) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double parseOverlap(String timezone1, String timezone2) {

		int num1 = Integer.parseInt(timezone1.substring(3, timezone1.length()));
		int num2 = Integer.parseInt(timezone2.substring(3, timezone2.length()));

		return Math.abs(num1 - num2);
	}

}
