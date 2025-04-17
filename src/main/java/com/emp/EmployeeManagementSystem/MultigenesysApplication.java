package com.emp.EmployeeManagementSystem;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "EmployeeManagement microservice REST API Documentation",
				description = "Department, Employee & Salary microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Trupti Arade",
						email = "truptiarade1414@gmail.com"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EmployeeManagementSystem microservice REST API Documentation",
				url = "http://localhost:8081/swagger-ui/index.html"
		)
)
public class MultigenesysApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MultigenesysApplication.class, args);
	}

}
