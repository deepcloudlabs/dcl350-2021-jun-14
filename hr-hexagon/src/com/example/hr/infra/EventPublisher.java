package com.example.hr.infra;

public interface EventPublisher<E> {
	void publishEvent(E event);
}
