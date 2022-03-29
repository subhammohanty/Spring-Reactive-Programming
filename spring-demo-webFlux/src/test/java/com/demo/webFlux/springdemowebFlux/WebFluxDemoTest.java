package com.demo.webFlux.springdemowebFlux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class WebFluxDemoTest {
	
	@Test
	public void demoMono() {
		Mono<?> monoString = Mono.just("Mono Demo")
				.then(Mono.error(new RuntimeException("Exception Occured")))
				.log();
		monoString.subscribe(System.out::println , e -> System.out.println(e.getMessage()));
	}
	
	@Test
	public void demoFlux() {
		Flux<String> justString = Flux.just("Spring" , "Spring Boot" , "Microservices")
				.concatWithValues("AWS")
				.concatWith(Flux.error(new RuntimeException("Exception Occured")))
				.log();
		justString.subscribe(System.out::println ,  e -> System.out.println(e.getMessage()));
	}
	
}
