package com.duggu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.duggu.entity.Country;
import com.duggu.repository.CountryRepo;
import com.duggu.service.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ServiceMackitoTests.class })
public class ServiceMackitoTests {
	@Mock
	CountryRepo countryrepo;

	@InjectMocks
	CountryService countryservice;
	List<Country> cityies;

	@Test
	@Order(2)
	public void getAllCities() {
		cityies = new ArrayList<>();
		cityies.add(new Country(11, "India", "Delhi"));
		cityies.add(new Country(22, "USA	", "Washington"));
		when(countryrepo.findAll()).thenReturn(cityies);
		assertEquals(2, countryservice.getAllCountries().size());

	}	

	@Test
	@Order(1)
	public void test_getCountryById() {

		cityies = new ArrayList<>();
		cityies.add(new Country(11, "Golu", "Row"));
		cityies.add(new Country(22, "SOnu", "Mhow"));
		int contryId = 11;
		when(countryrepo.findAll()).thenReturn(cityies);
		assertEquals(contryId, countryservice.getCountryById(contryId).getId());

	}

	@Test
	@Order(3)
	public void test_getCountryByName() {
		cityies = new ArrayList<>();
		cityies.add(new Country(1, "India", "Delhi"));
		cityies.add(new Country(2, "USA	", "Washington"));
		String contryName = "India";
		when(countryrepo.findAll()).thenReturn(cityies);
		assertEquals(contryName, countryservice.getCountryByName(contryName).getCountryName());

	}

	@Test
	@Order(4)
	public void test_addCountry() { 
		Country country = new Country(1, "Germani", "Berlin");  
		when(countryrepo.save(country)).thenReturn(country);
		assertEquals(country, countryservice.addCountry(country));

	}
	@Test
	@Order(5)
	public void test_countryUpdate() { 
		Country country = new Country(3, "Germani", "Berlin");  
		when(countryrepo.save(country)).thenReturn(country);
		assertEquals(country, countryservice.updateCountry(country));

	}
	@Test
	@Order(6)
	public void test_deleteCountry() { 
		Country country = new Country(3, "Germani", "Berlin");  
		countryservice.deleteCountry(country);
		verify(countryrepo,times(1)).delete(country);

	}

}
