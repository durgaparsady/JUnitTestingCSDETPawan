package com.duggu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Duggu API", version = "2.0", description = "JunitTesting Information"))
public class JUnitTestingCsdetPawanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JUnitTestingCsdetPawanApplication.class, args);
	}

}
