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

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.duggu.controller.CountryController;
import com.duggu.entity.Country;
import com.duggu.service.CountryService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureMockMvc
@ContextConfiguration
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PrecticeControllerTest.class })
public class PrecticControllerMvc {
	@Autowired
	MockMvc mockMvc;
	@Mock
	CountryService countryservice;

	@InjectMocks
	CountryController countrycontroller;

	
	@BeforeEach
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(countrycontroller).build();
	}
	List<Country>mycountries;

	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1, "India", "Dheli"));
		mycountries.add(new Country(2, "USA", "Washington"));
		when(countryservice.getAllCountries()).thenReturn(mycountries);// mockitoTest

      this.mockMvc.perform(get("/getcountries"))
      .andExpect(status().isFound())
      .andDo(print());
	}

	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
         Country country= new Country(2, "India", "Dheli");
	 ;
		int countryId=2;
	   when(countryservice.getCountryById(countryId)).thenReturn(country);
	   
	 this.mockMvc.perform(get("/getcountries/{id}",countryId))
	 .andExpect(status().isFound())
	 .andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
	 .andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
	 .andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Dheli"))
	 .andDo(print());
	}
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		Country country= new Country(2, "India", "Dheli");
	 
		String countryName="India";
		when(countryservice.getCountryByName(countryName)).thenReturn(country);
	//	otherOptions
	//	ResultActions response = mockMVC.perform(get("/registrationController/getByRegistration/name").param("name", "Vikash"));
		this.mockMvc.perform(get("/getcountry/{name}",countryName))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("India"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Dheli"))
		.andDo(print());
	}
	@Test
	@Order(3)
	public void test_addCountry() throws Exception {
	Country country=new Country(2, "USA", "Washington");
	when(countryservice.addCountry(country)).thenReturn(country);
	
	ObjectMapper mapper=new ObjectMapper();
	String jsonBody=mapper.writeValueAsString(country);
 
	this.mockMvc.perform(post("/addcountry").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isCreated())
	.andDo(print());
	  
	}
	@Test
	@Order(4)
	public void test_updateCountry() throws Exception {
		Country country=new Country(2, "USA", "Washington");
		Country update=new Country(2, "Usa","Washing");
		when(countryservice.getCountryById(country.getId())).thenReturn(country);
	country.setCountryCapital(update.getCountryCapital());
	country.setCountryName(update.getCountryName());
	country.setId(update.getId());
	System.out.println(country);
	int countryId=2;
	
	ObjectMapper mapper=new ObjectMapper();
	String jsonString=mapper.writeValueAsString(country);
	
	when(countryservice.updateCountry(country)).thenReturn(country);
	
	this.mockMvc.perform(put("/updatecountry/{id}",countryId).content(jsonString).contentType(MediaType.APPLICATION_JSON))
	.andExpect(status().isOk())
	.andDo(print()); 
	}
	@Test
	@Order(5)
	public void test_delete() throws Exception {
		Country country= new Country(2, "India", "Dheli");
		int countryId=2;
		when(countryservice.getCountryById(countryId)).thenReturn(country);
	   
		 countrycontroller.deleteCountry(countryId);
		 
		 this.mockMvc.perform(delete("/deletecountry/{id}",countryId))
		 .andExpect(status().isOk())
		 .andDo(print()); 
	}
	
	
}
