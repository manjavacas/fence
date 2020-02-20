package com.manjavacas.fence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.manjavacas.fence.model.Language;

@Repository
public interface LanguageRepository extends MongoRepository<Language, String> {

	Language findBy_id(String id);

	List<Language> findByEmployee(String dni);

	List<Language> findByLanguage(String language);

	void deleteBy_id(String id);

}
