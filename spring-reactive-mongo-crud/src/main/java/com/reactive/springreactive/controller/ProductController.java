package com.reactive.springreactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.springreactive.dto.ProductDto;
import com.reactive.springreactive.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public Flux<ProductDto> getAllProducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Mono<ProductDto> getProductById(@PathVariable String id) {
		return service.getProductById(id);
	}
	
	
	@GetMapping("/product-range")
	public Flux<ProductDto> getProductBetween(@RequestParam("min") double min , @RequestParam("max") double max) {
		return service.getProductsInRange(min, max);
	}
	
	@PostMapping
	public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
		return service.saveProduct(productDto);
	}
	
	@PutMapping("/update/{id}")
	public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto , @PathVariable String id) {
		return service.updateProduct(productDto, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void> deleteProduct(@PathVariable String id) {
		return service.deleteProducct(id);
	}

}
