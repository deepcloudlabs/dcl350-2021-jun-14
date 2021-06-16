package com.example.security.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="messageBroker",havingValue = "rabbit")
public class SecurityCardRabbitController {

	@RabbitListener(queues = "hr-queue")
	public void listenEmployeeEvent(String jsonEvent) {
		System.err.println("SecurityCardRabbitController has received an event: " + jsonEvent);
	}
}
