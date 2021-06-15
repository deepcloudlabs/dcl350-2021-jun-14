package com.example.hr.domain;

@ValueObject(entity = Employee.class)
public enum Department {
	HR, SALES, FINANCE, IT
}
