package com.example.crm.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CrmReactiveService;
import com.example.validation.TcKimlik;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CrmReactiveRestController {
	@Autowired
	private CrmReactiveService crmService;
	
	@GetMapping("{identity}")
	public Mono<CustomerDocument> findByIdentity(@PathVariable @Validated @TcKimlik String identity){
		return crmService.findCustomerById(identity);
	}
	
	@GetMapping
	public Flux<CustomerDocument> findByPage(
			@RequestParam @Validated @Min(0)           int page,
			@RequestParam @Validated @Min(10) @Max(50) int size){
		return crmService.findAllCustomers(page,size);
	}

	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody @Validated CustomerDocument customer){
		return crmService.addCustomer(customer);
	}
	
	@PutMapping("{identity}")
	public Mono<CustomerDocument> updateCustomer(
			@PathVariable @Validated @TcKimlik String identity,
			@RequestBody @Validated CustomerDocument customer){
		return crmService.updateCustomer(identity,customer);
	}
	
	@DeleteMapping("{identity}")
	public Mono<CustomerDocument> removeByIdentity(@PathVariable @Validated @TcKimlik String identity){
		return crmService.removeCustomerById(identity);
	}
	
}
