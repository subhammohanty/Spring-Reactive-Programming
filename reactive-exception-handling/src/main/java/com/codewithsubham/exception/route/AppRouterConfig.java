package com.codewithsubham.exception.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.codewithsubham.exception.handler.BookHandler;

@Configuration
public class AppRouterConfig {
	
	@Autowired
	private BookHandler handler;
	
	//This is for Exception Handling
	@Bean
	public WebProperties.Resources resources(){
		return new WebProperties.Resources();
	}
	
	
	@Bean
	public RouterFunction<ServerResponse> routeFunction(){
		return RouterFunctions.route()
				.GET("/route/books" , handler::getBooks)
				.GET("/route/books/{bookId}" , handler::getBooksById)
				.build();
	}

}
