package com.example.hr.event;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	protected TcKimlikNo kimlik;

	public EmployeeEvent(TcKimlikNo kimlik) {
		this.kimlik = kimlik;
	}

	public TcKimlikNo getKimlik() {
		return kimlik;
	}

}
