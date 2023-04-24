package com.duggu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.duggu.entity.Country;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PrecticeControllerIntigrationTest.class })
public class PrecticeControllerIntigrationTest {
					
	@Test
	@Order(1)
//    @Disabled
	public void test_getAllCountries() throws JSONException {
		String expected="[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"countryName\": \"Delhi\",\r\n"
				+ "        \"countryCapital\": \"India\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"countryName\": \"Washington\",\r\n"
				+ "        \"countryCapital\": \"USA\"\r\n"
				+ "    }\r\n"
				+ "]";
		
		TestRestTemplate template=new TestRestTemplate();
		ResponseEntity<String> response=template.getForEntity("http://localhost:8080/getcountries", String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(2)
	@Disabled
	public void test_getById() throws JSONException {
		String expected="{\r\n"
				+ "    \"id\": 2,\r\n"
				+ "    \"countryName\": \"Washington\",\r\n"
				+ "    \"countryCapital\": \"USA\"\r\n"
				+ "}";
		
		TestRestTemplate template=new TestRestTemplate();
		ResponseEntity<String>response=template.getForEntity("http://localhost:8080/getcountries/2", String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	@Test
	@Order(3)
	@Disabled
	public void test_getByName() throws JSONException {
		String expected="{\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"countryName\": \"Delhi\",\r\n"
				+ "    \"countryCapital\": \"India\"\r\n"
				+ "}";
		
		TestRestTemplate template=new TestRestTemplate();
		ResponseEntity<String>response=template.getForEntity("http://localhost:8080/getcountry/Delhi", String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	@Test
	@Order(4)
	@Disabled
	public void test_addCountry() throws JSONException {
		 Country country=new Country(3,"Duggu","Ram");
		
		String expected="{\r\n"
				+ "    \"id\": 3,\r\n"
				+ "    \"countryName\": \"Duggu\",\r\n"
				+ "    \"countryCapital\": \"Ram\"\r\n"
				+ "}";
		
		
		TestRestTemplate resttemplate=new TestRestTemplate();
	   HttpHeaders headers=new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    
	   HttpEntity<Country>request=new HttpEntity<Country>(country,headers);
    ResponseEntity<String> response= resttemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
	    
   JSONAssert.assertEquals(expected, response.getBody(), false); 
 	  
		
 	
// 		  TestRestTemplate resttemplate=new TestRestTemplate(); 
// 		  HttpHeaders headers=new HttpHeaders();
// 		  headers.setContentType(MediaType.APPLICATION_JSON);
// 		 HttpEntity<Country>request=new HttpEntity<Country>(country,headers); 
//	 
//		 ResponseEntity<String> response= resttemplate.postForEntity("http://localhost:8080/addcountry", request, String.class);
//		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(5)
	 @Disabled
	public void test_updateIntigreationCountry() throws JSONException {
		 Country country=new Country(3,"Yadav","Sahb");
		
		String expected="{\r\n"
				+ "    \"id\": 3,\r\n"
				+ "    \"countryName\": \"Yadav\",\r\n"
				+ "    \"countryCapital\": \"Sahb\"\r\n"
				+ "}";
		
		
		TestRestTemplate resttemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();  
	    HttpEntity<Country>request=new HttpEntity<Country>(country,headers);
	    
        ResponseEntity<String> response= resttemplate.exchange("http://localhost:8080/updatecountry/3", HttpMethod.PUT, request, String.class);  
        	System.out.println(response.getBody());
        	System.out.println("body  : "+response.getBody());
        	JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(6)
 @Disabled
	public void test_deleteIntigreationCountry() throws JSONException {
		Country country=new Country(3,"Yadav","Sahb");
		
		String expected="{\r\n"
				+ "    \"id\": 3,\r\n"
				+ "    \"countryName\": \"Yadav\",\r\n"
				+ "    \"countryCapital\": \"Sahb\"\r\n"
				+ "}";
		
		
		TestRestTemplate resttemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();  
		HttpEntity<Country>request=new HttpEntity<Country>(country,headers);
		//another way
		//ResponseEntity<String> response =	resttemplate.delete("http://localhost:8080/deletecountry/3");
		ResponseEntity<String> response= resttemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE, request, String.class);  
		System.out.println(response.getBody());
		System.out.println("body  : "+response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		assertEquals(HttpStatus.OK,response.getStatusCode());
	}
 	  
}		

//if i called get and post method then we have avaliable 2 method just exm.. template.postForEntity and template.getForEntity and its method avaliable for delete ex..template.delete(url).
//testRestTemplate have not PUT operation method hence "exchange" is provide this functionalty Perform the putOperation

//	ResponseEntity<String> response=testRestTemplate.exchange("http://localhost:8080/registrationController/update",HttpMethod.PUT,request, String.class);

