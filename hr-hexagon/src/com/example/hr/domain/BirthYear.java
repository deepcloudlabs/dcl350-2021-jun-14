package com.example.hr.domain;

@ValueObject(entity = Employee.class)
public final class BirthYear {
	private int value;

	private BirthYear(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static BirthYear of(int value) {
		if (value > 2005)
			throw new IllegalStateException("value must be less than 2006");
		return new BirthYear(value);
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}

}
