package com.duggu;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.duggu.controller.CountryController;
import com.duggu.entity.Country;
import com.duggu.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.duggu")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { ControllerMockMvcTest.class })
public class ControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CountryService countryservice;

	@InjectMocks
	CountryController countrycontroller;

	List<Country> mycountries;
	Country country;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(countrycontroller).build();
	}

	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1, "India", "Dheli"));
		mycountries.add(new Country(2, "USA", "Washington"));
		when(countryservice.getAllCountries()).thenReturn(mycountries);// mockitoTest

		this.mockMvc.perform(get("/getcountries"))// movkMvcTest
				.andExpect(status().isFound()).andDo(print());
	}

	@Test
	@Order(2)
	public void test_getCoutryById() throws Exception {

		country = new Country(1, "India", "Dheli");
		int countryId = 1;
		when(countryservice.getCountryById(countryId)).thenReturn(country);

		this.mockMvc.perform(get("/getcountries/{id}", countryId)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
				.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Dheli")).andDo(print());

	}

	@Test
	@Order(3)
	public void test_getCoutryByName() throws Exception {

		country = new Country(1, "India", "Dheli");
		String countryName = "India";
		when(countryservice.getCountryByName(countryName)).thenReturn(country);// movkito

		this.mockMvc.perform(get("/getcountry/{name}", countryName))// mvctest
				.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
				.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Dheli")).andDo(print());

	}

	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
		country = new Country(1, "India", "Dheli");
		when(countryservice.addCountry(country)).thenReturn(country);// mackito

		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(country);

		this.mockMvc.perform(post("/addcountry").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andDo(print());

	}

	@Test
	@Order(4)
	public void test_countryUpdate() throws Exception {
		int countryId = 1;
		country = new Country(1, "India", "Dheli");

		Country newUpdateCountry = new Country(2, "Indore", "Kgn");
		when(countryservice.getCountryById(country.getId())).thenReturn(country);

		country.setId(newUpdateCountry.getId());
		country.setCountryName(newUpdateCountry.getCountryName());
		country.setCountryCapital(newUpdateCountry.getCountryCapital());

		when(countryservice.updateCountry(country)).thenReturn(country);

		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(country);

		this.mockMvc
				.perform(
						put("/updatecountry/{id}", countryId).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());

	}
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception {
		country = new Country(1, "India", "Dheli");
		int countryId=1;
		when(countryservice.getCountryById(countryId)).thenReturn(country);
		
		this.mockMvc.perform(delete("/deletecountry/{id}",countryId))
		.andExpect(status().isOk())
		.andDo(print());
		
	}

}
