package com.demo.webFlux.springdemowebFlux.handler;

import com.demo.webFlux.springdemowebFlux.dao.CustomerDao;
import com.demo.webFlux.springdemowebFlux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandlerStream {
	
	@Autowired
	private CustomerDao dao;
	

	public Mono<ServerResponse> loadCustomerByStream(ServerRequest request){
		Flux<Customer> customersStream = dao.getCustomersStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customersStream, Customer.class);
	}
}
