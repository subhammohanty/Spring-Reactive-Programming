package com.demo.webFlux.springdemowebFlux.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.demo.webFlux.springdemowebFlux.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	public static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomers() {
		return IntStream.rangeClosed(1, 50)
				.peek(CustomerDao::sleepExecution)
				.peek(i -> System.out.println("Processing count : " + i))
				.mapToObj(i -> new Customer(i, "customer: " + i)).collect(Collectors.toList());
	}
	
	public Flux<Customer> getCustomersStream() {
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("Processing count : " + i))
				.map(i -> new Customer(i, "customer: " + i));
	}
	
	public Flux<Customer> getCustomersStreamForFunctionalEndPoint() {
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("Processing count : " + i))
				.map(i -> new Customer(i, "customer: " + i));
	}

}
