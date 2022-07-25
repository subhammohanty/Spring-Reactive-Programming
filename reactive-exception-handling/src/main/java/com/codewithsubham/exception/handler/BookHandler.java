package com.codewithsubham.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.codewithsubham.exception.dao.BookRepository;
import com.codewithsubham.exception.dto.Book;
import com.codewithsubham.exception.exception.BookAPIException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookHandler {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Mono<ServerResponse> getBooks(ServerRequest request){
		Flux<Book> books = bookRepository.getBooks();
		return ServerResponse.ok().body(books , Book.class);
	}
	
	public Mono<ServerResponse> getBooksById(ServerRequest request){
		int id = Integer.parseInt(request.pathVariable("bookId"));
		Mono<Book> switchIfEmpty = bookRepository.getBooks()
		.filter(book -> book.getId() == id)
		.next().switchIfEmpty(Mono.error(new BookAPIException("Book not found with id : " + id)));
		return ServerResponse.ok().body(switchIfEmpty , Book.class);
	}

}
