package com.reactive.springreactive;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import com.reactive.springreactive.controller.ProductController;
import com.reactive.springreactive.dto.ProductDto;
import com.reactive.springreactive.service.ProductService;

import net.bytebuddy.NamingStrategy.SuffixingRandom.BaseNameResolver.ForGivenType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
public class SpringReactiveMongoCrudApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private ProductService service;
	
	@Test
	public void saveProductTest() {
		Mono<ProductDto> productDto = Mono.just(new ProductDto("102", "Mobile", 2, 20000));
		when(service.saveProduct(productDto)).thenReturn(productDto);
		
		webTestClient.post().uri("/products")
		.body(Mono.just(productDto) , ProductDto.class)
		.exchange()
		.expectStatus().isOk();
	}
	
	@Test
	public void getProductsTest() {
		Flux<ProductDto> fluxProduct = Flux.just(new ProductDto("102", "Mobile", 2, 20000),
				new ProductDto("103", "TV", 1, 15000));
		when(service.getAllProducts()).thenReturn(fluxProduct);
		
		Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
		.exchange()
		.expectStatus().isOk()
		.returnResult(ProductDto.class)
		.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNext(new ProductDto("102", "Mobile", 2, 20000))
		.expectNext(new ProductDto("103", "TV", 1, 15000))
		.verifyComplete();
//		assertEquals(fluxProduct, responseBody);
	}
	
	@Test
	public void getProductsByIdTest() {
		Mono<ProductDto> productDto = Mono.just(new ProductDto("102", "Mobile", 2, 20000));
		when(service.getProductById(any())).thenReturn(productDto);
		
		Flux<ProductDto> responseBody = webTestClient.get().uri("/products/102")
		.exchange()
		.expectStatus().isOk()
		.returnResult(ProductDto.class)
		.getResponseBody();
		
		StepVerifier.create(responseBody)
		.expectSubscription()
		.expectNextMatches(p -> p.getName().equals("Mobile"))
		.verifyComplete();
	}
	
	@Test
	public void updateProductTest() {
		Mono<ProductDto> productDto = Mono.just(new ProductDto("102", "Mobile", 2, 20000));
		
		when(service.updateProduct(productDto, "102")).thenReturn(productDto);
		
		ResponseSpec ok = webTestClient.put().uri("/products/update/102")
		.body(Mono.just(productDto) , ProductDto.class)
		.exchange()
		.expectStatus().isOk();
		
	}
	
//	public void deleteProductTest() {
//	}

}
