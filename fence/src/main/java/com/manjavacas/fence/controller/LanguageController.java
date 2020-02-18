package com.manjavacas.fence.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Language;
import com.manjavacas.fence.service.LanguageService;

@RestController
public class LanguageController {

	@Autowired
	private LanguageService languageService;

	@RequestMapping("/Languages")
	public List<Language> getAllLanguages() {
		return languageService.getAllLanguages();
	}

	@RequestMapping("/Languages/{id}")
	public Language getLanguage(@PathVariable ObjectId id) {
		return languageService.getLanguage(id);
	}

	@RequestMapping("/Languages/employee/{dni}")
	public List<Language> getEmployeeLanguages(@PathVariable String dni) {
		return languageService.getEmployeeLanguages(dni);
	}

	@RequestMapping("/Languages/{language}")
	public List<Language> getSpeakers(@PathVariable String language) {
		return languageService.getSpeakers(language);
	}

	@PostMapping(value = "/Languages")
	public void addLanguage(@RequestBody Language Language) {
		languageService.addLanguage(Language);
	}

	@PutMapping(value = "/Languages/{id}")
	public void updateLanguage(@PathVariable ObjectId id, @RequestBody Language language) {
		languageService.updateLanguage(id, language);
	}

	@DeleteMapping("/Languages/{id}")
	public void deleteLanguage(@PathVariable ObjectId id) {
		languageService.deleteLanguage(id);
	}
}
