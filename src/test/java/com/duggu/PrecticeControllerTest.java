package com.duggu;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.duggu.controller.CountryController;
import com.duggu.entity.Country;
import com.duggu.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {PrecticeControllerTest.class})
public class PrecticeControllerTest {
	
	@Mock
	CountryService service;
	
	@InjectMocks
	CountryController contrycontroller;
	
	List<Country> countryList=new ArrayList<>();
	Country country;
	

	@Test
	@Order(1)
	public void test_getContries() { 
			countryList.add(new Country(1,"Indore","Ujjain"));
			countryList.add(new Country(2,"USA","Goa"));
			when(service.getAllCountries()).thenReturn(countryList);
			 ResponseEntity<List<Country>> listCountries=contrycontroller.getContries();
		 
			 assertEquals(HttpStatus.FOUND, listCountries.getStatusCode());
			 assertEquals( 2,listCountries.getBody().size());
			  
	}
	@Test
	@Order(2)
	public void test_getContryById() { 
		country = new Country(22, "USA", "Washington"); 
		int countryId=22;
		when(service.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res=contrycontroller.getCountryById(countryId);
		assertEquals(countryId, res.getBody().getId());
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		 
		
	}
	@Test
	@Order(3)
	public void test_getContryByName() { 
		country = new Country(22, "USA", "Washington"); 
		String countryName="USA";
		when(service.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res=contrycontroller.getCountyByName(countryName);
		assertEquals(countryName, res.getBody().getCountryName());
		assertEquals(HttpStatus.FOUND, res.getStatusCode()); 
		
	}
	@Test
	@Order(4)
	public void test_addCountry() { 
		country = new Country(222, "Japan", "Noida"); 
	 
		when(service.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res=contrycontroller.addCountry(country);
		assertEquals(country, res.getBody());
		assertEquals(HttpStatus.CREATED, res.getStatusCode()); 
			
	}
	@Test
	@Order(5)
	public void test_updateCountry() { 
		country = new Country(222, "Japan", "Noida"); 
		int countryId=222;
		Country updateCountry=new Country(222, "ShreeLanka", "Ayodhya"); 
       country.setCountryCapital(updateCountry.getCountryCapital());
       country.setCountryName(updateCountry.getCountryName());
       country.setId(updateCountry.getId()); 
       when(service.getCountryById(countryId)).thenReturn(country); 
      ResponseEntity<Country> res=contrycontroller.updateCountry(countryId, updateCountry);
       assertEquals(updateCountry, res.getBody());
       assertEquals(HttpStatus.OK, res.getStatusCode()); 
	}
	@Test
	@Order(6)
	public void test_deleteCountrty() {
		country = new Country(222, "Japan", "Noida");
		int countryId=222;
		 when(service.getCountryById(countryId)).thenReturn(country);
	      service.deleteCountry(country); 
		verify(service,times(1)).deleteCountry(country);
		ResponseEntity<Country>res=contrycontroller.deleteCountry(countryId);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(country, res.getBody());  
	}

}
