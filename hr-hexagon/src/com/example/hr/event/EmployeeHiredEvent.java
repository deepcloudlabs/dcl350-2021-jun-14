package com.example.hr.event;

import com.example.hr.domain.TcKimlikNo;

@DomainEvent
public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(TcKimlikNo kimlik) {
		super(kimlik);
	}

}
