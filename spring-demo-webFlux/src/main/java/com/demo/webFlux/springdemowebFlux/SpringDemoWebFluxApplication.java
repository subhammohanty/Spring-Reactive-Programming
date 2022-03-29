package com.demo.webFlux.springdemowebFlux;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Spring WebFlux Crud Example",
		version = "10",
		description = "Sample Documents"
))
public class SpringDemoWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoWebFluxApplication.class, args);
	}

}
