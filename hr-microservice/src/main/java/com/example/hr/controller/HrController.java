package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlik;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
@Validated
public class HrController { // Adapter -> Protocol [HTTP] Adapter
	@Autowired 
	private HrService hrService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request){
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("{kimlik}")
	public FireEmployeeResponse fireEmployee(@PathVariable @TcKimlik String kimlik){
		return hrService.fireEmployee(kimlik);		
	}
}
