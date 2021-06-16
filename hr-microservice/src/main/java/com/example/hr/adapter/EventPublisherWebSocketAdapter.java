package com.example.hr.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import com.example.hr.event.EmployeeEvent;
import com.example.hr.service.WebSocketService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(value="useWebSocket", havingValue = "true")
public class EventPublisherWebSocketAdapter {
	@Autowired
	private WebSocketService webSocketService;
	
	@Autowired
	private ObjectMapper mapper;
	
	@EventListener
	public void listenEvent(EmployeeEvent event) {
		webSocketService.getSessions()
		    .forEach((sessionId,session) -> {
		    	try {
		    		var json = mapper.writeValueAsString(event);
		    		WebSocketMessage<?> message = new TextMessage(json);
		    		session.sendMessage(message);	
		    	} catch (Exception e) {	}
		    });
	}

}
