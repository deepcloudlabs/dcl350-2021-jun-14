package com.example.crm.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	@Autowired
	private CustomerDocumentRepository customerRepo;
	
	public Mono<CustomerDocument> findCustomerById(String identity) {
		return customerRepo.findById(identity);
	}

	public Flux<CustomerDocument> findAllCustomers(int page, int size) {
		return customerRepo.sayfalayarakGetir(PageRequest.of(page, size));
	}

	public Mono<CustomerDocument> removeCustomerById(String identity) {
		Consumer<CustomerDocument> removeCustomer = cust -> {
			customerRepo.delete(cust).subscribe(System.err::println);
		};
		var customer = customerRepo.findById(identity);
		customer.subscribe(removeCustomer);
		return customer;
	}

	public Mono<CustomerDocument> addCustomer(CustomerDocument customer) {
		return customerRepo.insert(customer);
	}

	public Mono<CustomerDocument> updateCustomer(String identity, CustomerDocument customer) {
		return customerRepo.save(customer);
	}

}
