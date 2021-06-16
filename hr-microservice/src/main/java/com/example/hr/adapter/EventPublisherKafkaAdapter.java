package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hr.event.EmployeeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// GoF's Adapter Pattern: i) Class Adapter ii) Object Adapter 
@Service
@ConditionalOnProperty(name="messageBroker",havingValue = "kafka")
public class EventPublisherKafkaAdapter {
	@Autowired 
	private KafkaTemplate<String, String> kafkaTemplate; // (ii) Object Adapter
	
	@Autowired 
	private ObjectMapper objectMapper;
	
	@Value("${topicName}")
	private String topicName;
	
	@EventListener
	public void listenEvent(EmployeeEvent event) {
		try {
			var json = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(topicName, json);
		} catch (JsonProcessingException e) {
			System.err.println("Error in converting object to json: "+e.getMessage());
		}
	}

}
