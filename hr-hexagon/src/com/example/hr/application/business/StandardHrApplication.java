package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.event.EmployeeEvent;
import com.example.hr.event.EmployeeFiredEvent;
import com.example.hr.event.EmployeeHiredEvent;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private EmployeeRepository employeeRepository;
	private EventPublisher<EmployeeEvent> publisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<EmployeeEvent> publisher) {
		this.employeeRepository = employeeRepository;
		this.publisher = publisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		if (employeeRepository.exists(employee))
			throw new IllegalArgumentException("Employee already exists.");
		var hiredEmployee = employeeRepository.insert(employee);
		var event = new EmployeeHiredEvent(hiredEmployee.getKimlik());
		publisher.publishEvent(event);
		return hiredEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo identity) {
		Optional<Employee> firedEmployee = employeeRepository.removeByIdentity(identity);
		if (firedEmployee.isPresent()) {
			var event = new EmployeeFiredEvent(identity);
			publisher.publishEvent(event);
		}
		return firedEmployee;
	}

}
