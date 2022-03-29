package com.reactive.springreactive.repo;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.springreactive.entity.Product;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product, String>{

	Flux<Product> findByPriceBetween(Range<Double> priceRange);

}
