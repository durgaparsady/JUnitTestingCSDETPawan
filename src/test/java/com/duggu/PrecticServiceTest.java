package com.duggu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.grammars.hql.HqlParser.VariableContext;
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
@SpringBootTest(classes = {PrecticServiceTest.class})
public class PrecticServiceTest {
	
	@Mock
	CountryRepo repo;
	
  @InjectMocks
  CountryService service;
  
  List<Country> countryList =new ArrayList<>();
  Country country;
  @Test
  @Order(1)
  public void  test_getAllCountries() {
	  countryList.add(new Country(11, "Ujjain", "Indore"));
	  countryList.add(new Country(12, "USA", "Goa"));
	  when(repo.findAll()).thenReturn(countryList);
	  assertEquals(countryList, service.getAllCountries());
		 
	} 
  @Test
  @Order(2)
	public void test_getCountryById() {
		 countryList.add(new Country(11, "Ujjain", "Indore"));
		  countryList.add(new Country(12, "USA", "Goa"));
		  int countyId=12;
		  when(repo.findAll()).thenReturn(countryList);
		   assertEquals(countyId,service.getCountryById(countyId).getId());
	 
	} 
  @Test
  @Order(3)
  public void test_getCountryByName() {
		 countryList.add(new Country(11, "Ujjain", "Indore"));
		  countryList.add(new Country(12, "USA", "Goa"));
		  String countyName="Ujjain";
		  when(repo.findAll()).thenReturn(countryList);
		   assertEquals(countyName,service.getCountryByName(countyName).getCountryName()); 
		 
	} 
  @Test
  @Order(4)
  public void addCountry() {
	  country=new Country(11, "Ujjain", "Indore"); 
	  when(repo.save(country)).thenReturn(country);
	  assertEquals(country, service.addCountry(country));
	 
	} 
  @Test
  @Order(5)
	public void test_updateCountry() {
	  country=new Country(11, "Ujjain", "Indore");
	  Country update=new Country(11, "USA", "Goa");
	  when(repo.save(country)).thenReturn(update);
	  assertEquals(update, service.addCountry(update)); 
	} 
  @Test
  @Order(6)
  public void test_delete() {
	  country=new Country(11, "Ujjain", "Indore");
	  service.deleteCountry(country);
	 verify(repo,times(1)).delete(country);
	 
  } 
  

}
