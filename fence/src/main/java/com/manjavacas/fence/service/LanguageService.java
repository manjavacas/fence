package com.manjavacas.fence.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Language;
import com.manjavacas.fence.repository.LanguageRepository;

@Service
public class LanguageService {

	@Autowired
	LanguageRepository languageRepository;

	public List<Language> getAllLanguages() {
		return languageRepository.findAll();
	}

	public Language getLanguage(ObjectId id) {
		return languageRepository.findBy_id(id.toString());
	}

	public List<Language> getEmployeeLanguages(String dni) {
		return languageRepository.findByEmployee(dni);
	}

	public List<Language> getSpeakers(String language) {
		return languageRepository.findByLanguage(language);
	}

	public void addLanguage(Language language) {
		languageRepository.insert(language);
	}

	public void updateLanguage(ObjectId id, Language newLanguage) {
		Language currentLanguage = languageRepository.findBy_id(id.toString());

		currentLanguage.setEmployee(newLanguage.getLanguage());
		currentLanguage.setLanguage(newLanguage.getLanguage());

		languageRepository.save(currentLanguage);
	}

	public void deleteLanguage(ObjectId id) {
		languageRepository.deleteBy_id(id.toString());
	}

}
