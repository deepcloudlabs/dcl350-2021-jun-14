package com.example.hr.event;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	private static final AtomicInteger SEQUENCE_ID = new AtomicInteger();
	protected TcKimlikNo kimlik;
	protected String eventId;
	protected long eventSequenceId;
	protected long timeStamp;
	protected EmployeeEventType eventType;

	public EmployeeEvent(TcKimlikNo kimlik) {
		this.kimlik = kimlik;
		eventSequenceId = SEQUENCE_ID.incrementAndGet();
		eventId = UUID.randomUUID().toString();
		timeStamp = Instant.now().toEpochMilli();
	}

	public TcKimlikNo getKimlik() {
		return kimlik;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public long getEventSequenceId() {
		return eventSequenceId;
	}

	public void setEventSequenceId(long eventSequenceId) {
		this.eventSequenceId = eventSequenceId;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public EmployeeEventType getEventType() {
		return eventType;
	}

	public void setEventType(EmployeeEventType eventType) {
		this.eventType = eventType;
	}

}
