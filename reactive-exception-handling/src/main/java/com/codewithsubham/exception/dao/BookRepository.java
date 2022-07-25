package com.codewithsubham.exception.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.codewithsubham.exception.dto.Book;

import reactor.core.publisher.Flux;

@Repository
public class BookRepository {
	
	public Flux<Book> getBooks(){
		return Flux.range(1, 20)
				.map(i -> new Book(i, "Book" + i, new Random().nextDouble()));
	}

}
