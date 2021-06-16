package com.example.hr.event;

@DomainEvent
public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(String identity) {
		super(identity);
		setEventType(EmployeeEventType.EMPLOYEE_HIRED_EVENT);
	}

}
