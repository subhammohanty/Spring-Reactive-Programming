package com.demo.webFlux.springdemowebFlux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.demo.webFlux.springdemowebFlux.dao.CustomerDao;
import com.demo.webFlux.springdemowebFlux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	
	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request){
		Flux<Customer> customerList = customerDao.getCustomersStreamForFunctionalEndPoint();
		return ServerResponse.ok().body(customerList , Customer.class);
	}
	
	public Mono<ServerResponse> findCustomerById(ServerRequest request){
		int cId = Integer.parseInt(request.pathVariable("input"));
		Mono<Customer> next = customerDao.getCustomersStreamForFunctionalEndPoint().filter(c -> c.getId() == cId).next();
		return ServerResponse.ok().body(next , Customer.class);
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		Mono<Customer> bodyToMono = request.bodyToMono(Customer.class);
		Mono<String> map = bodyToMono.map(dto -> dto.getId() + ": " + dto.getName());
		return ServerResponse.ok().body(map , String.class);		
	}

}
