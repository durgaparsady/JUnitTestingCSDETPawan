package com.duggu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duggu.entity.Country;
import com.duggu.repository.CountryRepo;

@Service
public class CountryService {
	@Autowired
	private CountryRepo countryRepo; 
	//findall
	public List<Country> getAllCountries() {
		List<Country> countries = countryRepo.findAll();
		return countries;
	} 
	public Country getCountryById(int id) {
		List<Country> countries = countryRepo.findAll();
		Country country = null;
		for (Country con : countries) {
			if (con.getId() == id) {
				country = con;
			}
		}
		return country;
	} 
	// findbyname
	public Country getCountryByName(String name) {
		List<Country> countries = countryRepo.findAll();
		Country country = null;
		for (Country con : countries) { 
			if (con.getCountryName().equalsIgnoreCase(name)) {
				country = con; 
			}
		}
	 
		return country;
	} 
	// add
	public Country addCountry(Country country) {
       country.setId(getMaxId());
		countryRepo.save(country);
		return country;
	} 
	private int getMaxId() {
	 return countryRepo.findAll().size()+1;
		 
	} 
	// update
	public Country updateCountry(Country country) {
		countryRepo.save(country);
		return country;
	} 
   //delete
	public void deleteCountry(Country country) {
		countryRepo.delete(country); 
	}
	 

}
