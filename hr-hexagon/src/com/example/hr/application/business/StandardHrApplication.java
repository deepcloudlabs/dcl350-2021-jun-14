package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public class StandardHrApplication implements HrApplication {

	@Override
	public boolean hireEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		// TODO Auto-generated method stub
		return null;
	}

}