package com.duggu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duggu.controller.CountryController;
import com.duggu.entity.Country;
import com.duggu.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ControllerMackitoTests.class })
public class ControllerMackitoTests {
	@Mock
	CountryService countryservice;

	@InjectMocks
	CountryController countrycontroller;

	List<Country> mycountries;
	Country country;

	@Test
	@Order(1)
	public void test_getAllCountries() {
		mycountries = new ArrayList<>();
		mycountries.add(new Country(11, "India", "Delhi"));
		mycountries.add(new Country(22, "USA", "Washington"));

		when(countryservice.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res = countrycontroller.getContries();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2, res.getBody().size());

	}

	@Test
	@Order(2)
	public void test_getAllCountryById() {
		country = new Country(22, "USA", "Washington");
		int countryId = 22;
		when(countryservice.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countrycontroller.getCountryById(countryId);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryId, res.getBody().getId());
	}

	@Test
	@Order(3)
	public void test_getAllCountryByName() {
		country = new Country(22, "USA", "Washington");
		String countryName = "USA";
		when(countryservice.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countrycontroller.getCountyByName(countryName);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryName, res.getBody().getCountryName());

	}

	@Test
	@Order(4)
	public void test_addCountry() {
		country = new Country(22, "USA", "Washington");
		when(countryservice.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countrycontroller.addCountry(country);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country, res.getBody());
	}

	@Test
	@Order(5)
	public void test_updateCountry() {

		int countryId = 22;
		country = new Country(22, "Indore", "khargone");

		Country updateData = new Country(33, "Indore2", "Gogawan");

		when(countryservice.getCountryById(country.getId())).thenReturn(country);

		country.setId(updateData.getId());
		country.setCountryName(updateData.getCountryName());
		
		
		country.setCountryCapital(updateData.getCountryCapital());
		
		
		when(countryservice.updateCountry(country)).thenReturn(country); // internal call save method
		
		
		ResponseEntity<Country> res = countrycontroller.updateCountry(countryId, country);
		assertEquals(updateData, res.getBody());
		assertEquals(33, res.getBody().getId());

		// this code of youtbe
//		when(countryservice.getCountryById(countryId)).thenReturn(country);
//	    when(countryservice.updateCountry(country)).thenReturn(country);  
//	    ResponseEntity<Country> res= countrycontroller.updateCountry(countryId, country);
//		assertEquals(HttpStatus.OK, res.getStatusCode());
//		assertEquals(22, res.getBody().getId()); 
//		assertEquals("Indore", res.getBody().getCountryName()); 
//		assertEquals( "khargone", res.getBody().getCountryCapital()); 

	}

	@Test
	@Order(6)
	public void test_deleteTest() {
		country = new Country(123, "GOA", "ujjain");
		int countryId = 123;
		when(countryservice.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countrycontroller.deleteCountry(countryId);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(country, res.getBody());
		 

	}

}
