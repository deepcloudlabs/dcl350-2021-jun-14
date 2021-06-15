package com.example.hr.domain;

import java.util.Base64;
import java.util.Objects;

@ValueObject(entity = Employee.class)
public final class Photo {
	private final byte[] value;

	private Photo(byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}
	
	public String getBase64Value() {
		return Base64.getEncoder().encodeToString(value);
	}
	
	public static Photo valueOf(byte[] value) {
		Objects.requireNonNull(value, "Photo must be a valid png or jpeg data.");
		return new Photo(value);
	}
	
}
