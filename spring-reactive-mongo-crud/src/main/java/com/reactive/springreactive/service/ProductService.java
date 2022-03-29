package com.reactive.springreactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.reactive.springreactive.dto.ProductDto;
import com.reactive.springreactive.entity.Product;
import com.reactive.springreactive.repo.ProductRepo;
import com.reactive.springreactive.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo repo;
	
	public Flux<ProductDto> getAllProducts(){
		 Flux<ProductDto> map = repo.findAll().map(AppUtils::entityToDto);
		 System.out.println("Size : {} " + map.count());
		 return map;
	}
	
	public Mono<ProductDto> getProductById(String id){
		return repo.findById(id).map(AppUtils::entityToDto);
	}
	
	public Flux<ProductDto> getProductsInRange(double min , double max){
		return repo.findByPriceBetween(Range.closed(min, max)).map(AppUtils::entityToDto);
	}

	public Mono<ProductDto> saveProduct(Mono<ProductDto> productdto) {
		return productdto.map(AppUtils::dtoToEntity).flatMap(repo::insert).map(AppUtils::entityToDto);
	}
	
	public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, String id) {
		return repo.findById(id).flatMap(p->productDto.map(AppUtils::dtoToEntity).doOnNext(e -> e.setId(id)))
				.flatMap(repo::save)
				.map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteProducct(String id){
		return repo.deleteById(id);
	}
}
