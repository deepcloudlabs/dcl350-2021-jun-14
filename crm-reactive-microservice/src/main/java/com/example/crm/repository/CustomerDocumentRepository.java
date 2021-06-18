package com.example.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.document.CustomerDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// http://binkurt.blogspot.com/2015/02/mongodb-ile-calsmak.html

public interface CustomerDocumentRepository extends ReactiveMongoRepository<CustomerDocument, String>{
	@Query("{}")
	Flux<CustomerDocument> sayfalayarakGetir(Pageable page);
	Flux<CustomerDocument> findAllByLastName(String lastName);	
	Mono<CustomerDocument> findByEmail(String email);
}
