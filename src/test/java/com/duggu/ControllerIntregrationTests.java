package com.duggu;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(OrderAnnotation.class)
public class ControllerIntregrationTests {
  @Test
  @Order(1)
  public void getAllCountriesIntregrationTest() throws JSONException {
	  
	  String expected="[\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 1,\r\n"
	  		+ "        \"countryName\": \"Delhi\",\r\n"
	  		+ "        \"countryCapital\": \"India\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 2,\r\n"
	  		+ "        \"countryName\": \"Rajsthan\",\r\n"
	  		+ "        \"countryCapital\": \"Chittodghar\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 3,\r\n"
	  		+ "        \"countryName\": \"Ujjain\",\r\n"
	  		+ "        \"countryCapital\": \"Indore\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 4,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 5,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 6,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 7,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 8,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 9,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 10,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    },\r\n"
	  		+ "    {\r\n"
	  		+ "        \"id\": 11,\r\n"
	  		+ "        \"countryName\": \"Khargone\",\r\n"
	  		+ "        \"countryCapital\": \"Bamnala\"\r\n"
	  		+ "    }\r\n"
	  		+ "]";
	  
	  TestRestTemplate resttemplate=new TestRestTemplate();
	  ResponseEntity<String> response=resttemplate.getForEntity("http://localhost:8080/getcountries",String.class);
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getBody());
	  JSONAssert.assertEquals(expected, response.getBody(), false);

	  
  }
  @Test
  @Order(2)
  public void getCountrtyByIdIntregrationTest() throws JSONException {
	  
	  String expected= "{\r\n"
	  		+ "    \"id\": 3,\r\n"
	  		+ "    \"countryName\": \"Ujjain\",\r\n"
	  		+ "    \"countryCapital\": \"Indore\"\r\n"
	  		+ "}";
	  
	  TestRestTemplate resttemplate=new TestRestTemplate();
	  ResponseEntity<String> response=resttemplate.getForEntity("http://localhost:8080/getcountries/3",String.class);
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getBody());
	  JSONAssert.assertEquals(expected, response.getBody(), false); 
  }
  @Test
  @Order(3)
  public void getCountrtyByNameIntregrationTest() throws JSONException {
	  
	  String expected=  "{\r\n"
	  		+ "    \"id\": 1,\r\n"
	  		+ "    \"countryName\": \"Delhi\",\r\n"
	  		+ "    \"countryCapital\": \"India\"\r\n"
	  		+ "}";
	  
	  TestRestTemplate resttemplate=new TestRestTemplate();
	  ResponseEntity<String> response=resttemplate.getForEntity("http://localhost:8080/getcountry/Delhi",String.class);
	  System.out.println(response.getStatusCode());
	  System.out.println(response.getBody());
	  JSONAssert.assertEquals(expected, response.getBody(), false); 
	  
  }
//  @Test
//  @Order(4)
//  public void addCountryIntregrationTest() throws JSONException { 
//	  
//	  Country country=new Country(9,"Khargone","Bamnala");  
//	  String expected=  " {\r\n"	
//	  		+ "     \"id\":9,\r\n"
//	  		+ "     \"countryName\":\"Khargone\",\r\n"
//	  		+ "     \"countryCapital\":\"Bamnala\"\r\n"
//	  		+ " }";
//	  
// 
//	  TestRestTemplate resttemplate=new TestRestTemplate();
//   HttpHeaders headers=new HttpHeaders();
//   headers.setContentType(MediaType.APPLICATION_JSON);
//   
//   HttpEntity<Country>request=new HttpEntity<Country>(country,headers);
//   ResponseEntity<String> response= resttemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
//   
//   JSONAssert.assertEquals(expected, response.getBody(), false); 
//	  
//  }
  
}
