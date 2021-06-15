package com.example.hr.domain;

import java.util.Objects;

@ValueObject(entity = Employee.class)
public final class FullName {
	private final String firstName;
	private final String lastName;

	private FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public static FullName of(String firstName, String lastName) {
		Objects.requireNonNull(firstName,"You must provide a first name.");
		Objects.requireNonNull(lastName,"You must provide a last name.");
		return new FullName(firstName, lastName);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	
}
