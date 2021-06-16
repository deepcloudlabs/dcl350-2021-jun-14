package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.event.EmployeeEvent;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// GoF's Adapter Pattern: i) Class Adapter ii) Object Adapter 
@Service
public class EventPublisherKafkaAdapter implements EventPublisher<EmployeeEvent> {
	@Autowired 
	private KafkaTemplate<String, String> kafkaTemplate; // (ii) Object Adapter
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@Value("${topicName}")
	private String topicName;
	
	@Override
	public void publishEvent(EmployeeEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(topicName, json);
		} catch (JsonProcessingException e) {
			System.err.println("Error in converting object to json: "+e.getMessage());
		}
	}

}
