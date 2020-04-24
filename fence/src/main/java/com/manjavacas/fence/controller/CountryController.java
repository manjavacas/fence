package com.manjavacas.fence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manjavacas.fence.model.Country;
import com.manjavacas.fence.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping("/Countries")
	public List<Country> getAllCountrys() {
		return countryService.getAllCountries();
	}

	@RequestMapping("/Countries/{name}")
	public Country getCountry(@PathVariable String name) {
		return countryService.getCountry(name);
	}

	@PutMapping(value = "/Countries/{name}")
	public void updateCountry(@PathVariable String name, @RequestBody Country country) {
		countryService.updateCountry(name, country);
	}

	@DeleteMapping("/Countries/{name}")
	public void deleteCountry(@PathVariable String name) {
		countryService.deleteCountry(name);
	}
}
