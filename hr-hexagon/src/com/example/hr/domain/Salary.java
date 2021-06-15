package com.example.hr.domain;

import java.util.Objects;

@ValueObject(entity = Employee.class)
public final class Salary {
	private final double value;
	private final FiatCurrency currency;

	private Salary(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public static Salary of(double value) {
		return of(value, FiatCurrency.TL);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salary other = (Salary) obj;
		if (currency != other.currency)
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	public static Salary of(double value, FiatCurrency currency) {
		if (value < 3_500.0)
			throw new IllegalArgumentException("value must be greater than minimum salary.");
		Objects.requireNonNull(currency, "You must provide a currency");
		return new Salary(value, currency);
	}

	@Override
	public String toString() {
		return "Salary [value=" + value + ", currency=" + currency + "]";
	}
	
	
}
