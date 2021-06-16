package com.example.hr.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketService implements WebSocketHandler {
	private Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	
	public Map<String, WebSocketSession> getSessions() {
		return sessions;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.err.println("Error: "+exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
