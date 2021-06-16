package com.example.hr.event;

import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public class EmployeeFiredEvent extends EmployeeEvent{
	
	public EmployeeFiredEvent(TcKimlikNo kimlik) {
		super(kimlik);
		setEventType(EmployeeEventType.EMPLOYEE_FIRED_EVENT);
	}

	
}
