package com.example.security.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="messageBroker",havingValue = "kafka")
public class SecurityCardKafkaController {

	@KafkaListener(topics = "hr-events",groupId = "security-card")
	public void listenEmployeeEvent(String jsonEvent) {
		System.err.println("SecurityCardKafkaController has received an event: " + jsonEvent);
	}
}
