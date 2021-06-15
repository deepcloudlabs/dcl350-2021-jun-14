package com.example.hr.domain;

// DDD: Bounded Context, Ubiquitous Language Entity Class
// 1) Have identity
// 2) Mutable Class
@BoundedContext(name="hr",subdomain="hr") // meta-data -> just lives in the source code
@EntityClass(identities= "kimlik")
public class Employee {
	private TcKimlikNo kimlik;
	private FullName fullname;
	private Iban iban;
	private Salary salary;
	private BirthYear birthYear;
	private JobStyle style;
	private List<Department> departments; // multiplicity -> [], List 
	private Photo photo;
}
