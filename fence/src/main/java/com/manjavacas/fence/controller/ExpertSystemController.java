package com.manjavacas.fence.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.CG;
import com.manjavacas.fence.model.Country;
import com.manjavacas.fence.model.Employee;
import com.manjavacas.fence.model.Recommendation;
import com.manjavacas.fence.service.CGservice;
import com.manjavacas.fence.service.CountryService;
import com.manjavacas.fence.service.EmployeeService;
import com.manjavacas.fence.service.MinGapService;
import com.manjavacas.fence.service.RecommendationService;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

@RestController
public class ExpertSystemController {

	// Expert system constants
	private final static String RULES_RESOURCE = "classpath:rules/rules-communication.fcl";
	private final static String FUNCTION_BLOCK = "recommender";

	private final static int N_COMMUNICATION_SOLUTIONS = 9;

	// Solutions provided by the expert system
	private final static String SOLUTION_COMMUNICATION_1 = "asynchronous means are recommended given a low time overlap. "
			+ "The communication is adapted to the age of the users and their level of English, proposing as main means of communication e-mail and conventional documentation.";
	private final static String SOLUTION_COMMUNICATION_2 = "asynchronous means are recommended given the low time overlap. "
			+ "Given an average fluency in English, Wikis and e-mail are encouraged as the main means of communication, with the possibility of relying on conventional documentation.";
	private final static String SOLUTION_COMMUNICATION_3 = "asynchronous means adapted to low time overlap are recommended. "
			+ "Given the high level of English of both users, email, chats and Wikis are recommended as the main means of communication, depending on the circumstances.";
	private final static String SOLUTION_COMMUNICATION_4 = "synchronous means are advisable in view of the high time overlap. "
			+ "Given the high level of English of the users, the use of the conventional phone calls is recommended.";
	private final static String SOLUTION_COMMUNICATION_5 = "synchronous media that encourage communication at low levels of English, "
			+ "such as chat rooms, are recommended.";
	private final static String SOLUTION_COMMUNICATION_6 = "asynchronous means are recommended given the low time overlap. "
			+ "As there is a common command of English, corporate chats are recommended as the main means of communication (e.g. Teams or Jira).";
	private final static String SOLUTION_COMMUNICATION_7 = "asynchronous means are recommended to suit the low level of English of the users. "
			+ "Recommended media capable of meeting these requirements are Slack and Github.";
	private final static String SOLUTION_COMMUNICATION_8 = "synchronous means are recommended as the time overlap is high. "
			+ "Given the high level of English of the users, video calls through applications such as Zoom, Teams, Skype, Hangouts, etc. are recommended.";
	private final static String SOLUTION_COMMUNICATION_9 = "synchronous media adapted to a low level of English are recommended, "
			+ "such as synchronous chats from tools like Zoom, Teams, Skype, WhatsApp, Telegram, etc.";

	private final static String SOLUTION_MEDIATOR = "given the high cultural differences between users, a mediator is recommended "
			+ "to intercede between them in order to ensure proper communication.";
	private final static String SOLUTION_TRAINING = "socio-cultural training is recommended to reinforce communication between users: "
			+ "manuals, training or any other awareness solution.";
	private final static String SOLUTION_SUPERVISOR = "given the low user's experience, the assignment of the following supervisor is proposed: ";

	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private CGservice cgService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private MinGapService minGapService;

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping("/Recommendations/{project}")
	public List<String> getRecommendations(@PathVariable String project) {
		List<Recommendation> recommendations = recommendationService.getRecommendationsByProject(project);

		ArrayList<String> formattedRecommendations = new ArrayList<String>();
		for (Recommendation recommendation : recommendations) {
			formattedRecommendations.add(recommendation.toString());
		}

		return formattedRecommendations;
	}

	@RequestMapping("/Recommendations/calculate/{project}")
	public List<String> calculateRecommendations(@PathVariable String project) throws IOException {
		return runRecommender(project);
	}

	public List<String> runRecommender(String project) throws IOException {

		List<Recommendation> recommendations = new ArrayList<Recommendation>();
		ArrayList<String> alreadySupervised = new ArrayList<String>();

		// Get different tuples of users in CG
		List<CG> gaps = cgService.getCGByProject(project);

		// Load fuzzy inference system
		Resource resource = resourceLoader.getResource(RULES_RESOURCE);
		FIS fis = FIS.load(resource.getFile().getAbsolutePath());

		// Get the recommender function block
		FunctionBlock fb = fis.getFunctionBlock(FUNCTION_BLOCK);

		// Input
		for (CG gap : gaps) {

			if (gap.getWeight() >= minGapService.getMinGapWeight(gap.getUser1(), gap.getUser2(), project)) {

				Employee user1 = employeeService.getEmployee(gap.getUser1());
				Employee user2 = employeeService.getEmployee(gap.getUser2());

				// Set variables
				fb.setVariable("age1", user1.getAge());
				fb.setVariable("age2", user2.getAge());

				fb.setVariable("englishLevel", (user1.getEnglishLevelNum() + user2.getEnglishLevelNum()) / 2);

				fb.setVariable("experience1", user1.getExperienceNum());
				fb.setVariable("experience2", user2.getExperienceNum());

				fb.setVariable("overlap", parseOverlap(user1.getTimezone(), user2.getTimezone()));
				fb.setVariable("culturalDist", parseCulturalDist(user1.getCountry(), user2.getCountry()));

				// JFuzzyChart.get().chart(fb);

				// Evaluate
				fb.evaluate();

				// Get recommended solution
				String recommended = null;
				for (int i = 1; i <= N_COMMUNICATION_SOLUTIONS; i++) {
					Variable solution = fb.getVariable("solution" + i);
					// JFuzzyChart.get().chart(solution, solution.getDefuzzifier(), true);
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
					case "solution5":
						solutionText += SOLUTION_COMMUNICATION_5;
						break;
					case "solution6":
						solutionText += SOLUTION_COMMUNICATION_6;
						break;
					case "solution7":
						solutionText += SOLUTION_COMMUNICATION_7;
						break;
					case "solution8":
						solutionText += SOLUTION_COMMUNICATION_8;
						break;
					case "solution9":
						solutionText += SOLUTION_COMMUNICATION_9;
						break;
					}

					// Save communication solution
					Recommendation newRecommendation = new Recommendation(user1.getName(), user2.getName(),
							solutionText, project);
					if (!recommendations.contains(newRecommendation)) {
						recommendations.add(newRecommendation);
					}

					// Get mediator solution
					if (fb.getVariable("mediator").getValue() != 0) {
						Recommendation newRecommendationMediator = new Recommendation(user1.getName(), user2.getName(),
								SOLUTION_MEDIATOR, project);
						if (!recommendations.contains(newRecommendationMediator)) {
							recommendations.add(newRecommendationMediator);
						}
					}

					// Get training solution
					if (fb.getVariable("training").getValue() != 0) {
						Recommendation newRecommendationTraining = new Recommendation(user1.getName(), user2.getName(),
								SOLUTION_TRAINING, project);
						if (!recommendations.contains(newRecommendationTraining)) {
							recommendations.add(newRecommendationTraining);
						}
					}

					// Get supervisor1 solution
					if ((fb.getVariable("supervisor1").getValue() != 0)
							&& (!alreadySupervised.contains(user1.getDni()))) {
						String supervisor = employeeService.getSupervisor(user1.getTeam());
						recommendations
								.add(new Recommendation(user1.getName(), SOLUTION_SUPERVISOR + supervisor, project));
						alreadySupervised.add(user1.getDni());
					}

					// Get supervisor2 solution
					if ((fb.getVariable("supervisor2").getValue() != 0)
							&& (!alreadySupervised.contains(user2.getDni()))) {
						String supervisor = employeeService.getSupervisor(user2.getTeam());
						recommendations
								.add(new Recommendation(user2.getName(), SOLUTION_SUPERVISOR + supervisor, project));
						alreadySupervised.add(user2.getDni());
					}
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

		// Return normalized
		return culturalDistance / 100;
	}

	private double parseOverlap(String timezone1, String timezone2) {

		int num1 = Integer.parseInt(timezone1.substring(3, timezone1.length()));
		int num2 = Integer.parseInt(timezone2.substring(3, timezone2.length()));

		double timeDif = 0;
		if (num1 >= num2) {
			timeDif = num1 - num2;
		} else {
			timeDif = num2 - num1;
		}

		// Being 26 the maximum time difference in the world
		return 1 - (timeDif / 26);
	}

}
