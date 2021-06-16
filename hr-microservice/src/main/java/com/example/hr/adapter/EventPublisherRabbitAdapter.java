package com.example.hr.adapter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.hr.event.EmployeeEvent;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name="messageBroker",havingValue = "rabbit")
public class EventPublisherRabbitAdapter implements EventPublisher<EmployeeEvent> {
	@Autowired 
	private RabbitTemplate rabbitTemplate; 
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@Value("${exchangeName}")
	private String exchangeName;
	
	@Override
	public void publishEvent(EmployeeEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			rabbitTemplate.convertAndSend(exchangeName, null, json);
		} catch (JsonProcessingException e) {
			System.err.println("Error in converting object to json: "+e.getMessage());
		}
	}

}
