package com.duggu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;

import com.duggu.entity.Country;
import com.duggu.repository.CountryRepo;
 
@DataJpaTest 
@WebAppConfiguration
@SpringBootTest(classes = {RepoTest.class})
public class RepoTest {
	  

	    @Autowired
	    private CountryRepo countryrepo;
	    @Test
	    @Rollback(false)
	    public void testSaveUser() {
	        Country country = new Country(1,"Ujjain", "pass"); 
	        Country resName = countryrepo.findByCountryName("Ujjain");
	        System.out.println(resName);
	  
	    }
}
