package com.manjavacas.fence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manjavacas.fence.model.Country;

public interface CountryRepository extends MongoRepository<Country, String> {

	Country findByName(String name);

	void deleteByName(String name);

}
