package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hr.event.EmployeeEvent;
import com.example.hr.infra.EventPublisher;

@Service
public class EventPublisherInternalAdapter implements EventPublisher<EmployeeEvent> {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Override
	public void publishEvent(EmployeeEvent event) {
		eventPublisher.publishEvent(event);
	}

}
