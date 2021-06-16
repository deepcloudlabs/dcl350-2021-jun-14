package com.example.hr.event;

@DomainEvent
public class EmployeeFiredEvent extends EmployeeEvent{
	
	public EmployeeFiredEvent(String identity) {
		super(identity);
		setEventType(EmployeeEventType.EMPLOYEE_FIRED_EVENT);
	}

	
}
