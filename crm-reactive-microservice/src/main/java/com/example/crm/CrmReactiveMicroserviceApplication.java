package com.example.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// curl -X POST "http://localhost:2300/customers" -d "{\"identity\": \"11111111110\", \"firstName\": \"jack\", \"lastName\": \"bauer\", \"email\": \"jack@example.com\", \"sms\": \"+905555555555\"}" -H "Content-Type: application/json" -H "Accept: application/json" 
// curl -X POST "http://localhost:2300/customers" -d "{\"identity\": \"47628211630\", \"firstName\": \"kate\", \"lastName\": \"austen\", \"email\": \"kate@example.com\", \"sms\": \"+905555554444\"}" -H "Content-Type: application/json" -H "Accept: application/json" 
// curl -X PUT "http://localhost:2300/customers/47628211630" -d "{\"identity\": \"47628211630\", \"firstName\": \"kate\", \"lastName\": \"austen\", \"email\": \"kate@example.com\", \"sms\": \"+905555553333\"}" -H "Content-Type: application/json" -H "Accept: application/json" 
// curl "http://localhost:2300/customers/47628211630" -H "Accept: application/json" 
// curl "http://localhost:2300/customers?page=0&size=10" -H "Accept: application/json"
// curl -X DELETE "http://localhost:2300/customers/47628211630" -H "Accept: application/json"

@SpringBootApplication
public class CrmReactiveMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmReactiveMicroserviceApplication.class, args);
	}

}
