package com.manjavacas.fence.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.model.Country;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Recommendation;
import com.manjavacas.fence.service.CGservice;
import com.manjavacas.fence.service.CountryService;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.RecommendationService;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@RestController
public class ExpertSystemController {

	private final static String FILE = "/fence/src/main/resources/rules/rules.fcl";
	private final static String BLOCK = "recommender";

	private final static int N_COMMUNICATION_SOLUTIONS = 4;

	// Solutions provided by the expert system
	private final static String SOLUTION_COMMUNICATION_1 = "given the poor time overlap between users, "
			+ "asynchronous methods are recommended. At the same time, the following (high) age-appropriate communication methods are proposed: ";
	private final static String SOLUTION_COMMUNICATION_2 = "since there is an acceptable time overlap, "
			+ "synchronous media are proposed. These media are adjusted to the users' age (high): ";
	private final static String SOLUTION_COMMUNICATION_3 = "given the poor time overlap between users, "
			+ "asynchronous methods are recommended. At the same time, the following (low/medium) age-appropriate communication methods are proposed: ";
	private final static String SOLUTION_COMMUNICATION_4 = "since there is an acceptable time overlap, "
			+ "synchronous media are proposed. These media are adjusted to the users' age (low/medium): ";
	private final static String SOLUTION_MEDIATOR = "given the high cultural differences between users, "
			+ "a mediator is recommended to intercede between the two in order to ensure proper communication.";
	private final static String SOLUTION_TRAINING = "socio-cultural training is recommended to reinforce communication between users: manuals, "
			+ "training or any other awareness solution.";
	private final static String SOLUTION_SUPERVISOR = "given the low user's experience, the assignment of the following supervisor is proposed: ";

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private CGservice cgService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CountryService countryService;

	@RequestMapping("/Recommendations")
	public List<String> getRecommendations() {
		List<Recommendation> recommendations = recommendationService.getAllRecommendations();

		ArrayList<String> formattedRecommendations = new ArrayList<String>();
		for (Recommendation recommendation : recommendations) {
			formattedRecommendations.add(recommendation.toString());
		}

		return formattedRecommendations;
	}

	@RequestMapping("/Recommendations/calculate")
	public List<String> calculateRecommendations() {
		return runRecommender();
	}

	public List<String> runRecommender() {

		List<Recommendation> recommendations = new ArrayList<Recommendation>();

		// Get all coordination gaps
		List<CG> gaps = cgService.getAllCG();

		// Load fuzzy inference system
		FIS fis = FIS.load(FILE);

		// Get the recommender function block
		FunctionBlock fb = fis.getFunctionBlock(BLOCK);

		// JFuzzyChart.get().chart(fb);

		// Input
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
			for (int i = 1; i <= N_COMMUNICATION_SOLUTIONS; i++) {
				Variable solution = fb.getVariable("solution" + i);
				// JFuzzyChart.get().chart(solution, risk.getDefuzzifier(), true);
				if (solution.getValue() != 0) {
					recommended = solution.getName();
				}
			}

			if (recommended != null) {

				// Get communication solution
				String solutionText = "";
				switch (recommended) {
				case "solution1":
					solutionText += SOLUTION_COMMUNICATION_1;
					break;
				case "solution2":
					solutionText += SOLUTION_COMMUNICATION_2;
					break;
				case "solution3":
					solutionText += SOLUTION_COMMUNICATION_3;
					break;
				case "solution4":
					solutionText += SOLUTION_COMMUNICATION_4;
					break;
				}

				recommendations.add(new Recommendation(user1.getName(), user2.getName(), solutionText));

				// Get mediator solution
				if (fb.getVariable("mediator").getValue() == 1) {
					recommendations.add(new Recommendation(user1.getName(), user2.getName(), SOLUTION_MEDIATOR));
				}

				// Get training solution
				if (fb.getVariable("training").getValue() == 1) {
					recommendations.add(new Recommendation(user1.getName(), user2.getName(), SOLUTION_TRAINING));
				}

				// Get supervisor solutions
				if (user1.getExperience().equals("LOW") || user1.getExperience().equals("VERY LOW")) {
					String supervisor = employeeService.getSupervisor(user1.getTeam());
					recommendations.add(new Recommendation(user1.getName(), SOLUTION_SUPERVISOR + supervisor));
				}
				if (user2.getExperience().equals("LOW") || user2.getExperience().equals("VERY LOW")) {
					String supervisor = employeeService.getSupervisor(user2.getTeam());
					recommendations.add(new Recommendation(user2.getName(), SOLUTION_SUPERVISOR + supervisor));
				}
			}

		}

		// Update database
		recommendationService.updateRecommendations(recommendations);

		// Return recommendations with format
		ArrayList<String> formattedRecommendations = new ArrayList<String>();
		for (Recommendation recommendation : recommendations) {
			formattedRecommendations.add(recommendation.toString());
		}

		return formattedRecommendations;

	}

	private double parseCulturalDist(String name1, String name2) {

		Country country1 = countryService.getCountry(name1);
		Country country2 = countryService.getCountry(name2);

		// Compute distances for each dimension
		int pdiDif = Math.abs(country1.getPdi() - country2.getPdi());
		int idvDif = Math.abs(country1.getIdv() - country2.getIdv());
		int masDif = Math.abs(country1.getMas() - country2.getMas());
		int uaiDif = Math.abs(country1.getUai() - country2.getUai());
		int ltowvsDif = Math.abs(country1.getLtowvs() - country2.getLtowvs());
		int ivrDif = Math.abs(country1.getIvr() - country2.getIvr());

		// Calculate cultural distance as the mean of all distances
		double culturalDistance = (pdiDif + idvDif + masDif + uaiDif + ltowvsDif + ivrDif) / 6;

		return culturalDistance;
	}

	private double parseOverlap(String timezone1, String timezone2) {

		int num1 = Integer.parseInt(timezone1.substring(3, timezone1.length()));
		int num2 = Integer.parseInt(timezone2.substring(3, timezone2.length()));

		return Math.abs(num1 - num2);
	}

}
