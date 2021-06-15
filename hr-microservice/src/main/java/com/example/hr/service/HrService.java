package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Service
public class HrService {

	@Autowired
	private HrApplication hrApplication;
	@Autowired
	private ModelMapper modelMapper;
	
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var hiredEmployee = hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("success");
	}

	public FireEmployeeResponse fireEmployee(String kimlik) {
		var firedEmployee = hrApplication.fireEmployee(TcKimlikNo.valueOf(kimlik));
		if (firedEmployee.isEmpty())
			return new FireEmployeeResponse("failure");
		return new FireEmployeeResponse("success");
	}

}
