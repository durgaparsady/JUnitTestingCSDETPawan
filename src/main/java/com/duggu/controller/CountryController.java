package com.duggu.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duggu.entity.Country;
import com.duggu.repository.CountryRepo;
import com.duggu.service.CountryService;

@RestController
public class CountryController {
	@Autowired
	CountryService countryService;
	@Autowired
	CountryRepo repo;

	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getContries() {
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (Exception e) {

			return new ResponseEntity<List<Country>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id") int id) {
		try {
			Country country = countryService.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcountry/{name}")
	public ResponseEntity<Country> getCountyByName(@PathVariable(value = "name") String name) {
		try { 
			Country country = countryService.getCountryByName(name);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		try {
			country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.CREATED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
		try {
			Country existCountry = countryService.getCountryById(id);
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());

			Country update_country = countryService.updateCountry(existCountry);
			return new ResponseEntity<Country>(country, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(path = "/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
		Country country = null;
		try {
			country = countryService.getCountryById(id);
			countryService.deleteCountry(country);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getname/{name}")
	public ResponseEntity<Country> getFindByname(@PathVariable String name){
		    Country country= repo.findByCountryName(name);
		    return new ResponseEntity<Country>(country,HttpStatus.FOUND);
		    
	}
	
 
	
}
