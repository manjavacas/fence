package com.manjavacas.fence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjavacas.fence.model.Country;
import com.manjavacas.fence.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	public Country getCountry(String name) {
		return countryRepository.findByName(name);
	}

	public void updateCountry(String name, Country newCountry) {
		Country currentCountry = countryRepository.findByName(name);

		if (currentCountry == null) {
			currentCountry = new Country();
		}

		currentCountry.setName(newCountry.getName());
		currentCountry.setPdi(newCountry.getPdi());
		currentCountry.setIdv(newCountry.getIdv());
		currentCountry.setMas(newCountry.getMas());
		currentCountry.setUai(newCountry.getUai());
		currentCountry.setLtowvs(newCountry.getLtowvs());

		countryRepository.save(currentCountry);
	}

	public void deleteCountry(String name) {
		countryRepository.deleteByName(name);
	}

}
