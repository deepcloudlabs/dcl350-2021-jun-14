package com.example.hr.domain;

import java.util.List;

// DDD: Bounded Context, Ubiquitous Language Entity Class
// 1) Have identity
// 2) Mutable Class
// Entity Root -> at the root of Object Graph -> Aggregate -> Entity
@BoundedContext(name="hr",subdomain="hr") // meta-data -> just lives in the source code
@EntityClass(identities= "kimlik", aggregate = true)
public class Employee {
	private TcKimlikNo kimlik;
	private FullName fullname;
	private Iban iban;
	private Salary salary;
	private BirthYear birthYear;
	private JobStyle style;
	private List<Department> departments; // multiplicity -> [], List 
	private Photo photo;
	
	public Employee(TcKimlikNo kimlik, FullName fullname, Iban iban, Salary salary, BirthYear birthYear, JobStyle style,
			List<Department> departments, Photo photo) {
		this.kimlik = kimlik;
		this.fullname = fullname;
		this.iban = iban;
		this.salary = salary;
		this.birthYear = birthYear;
		this.style = style;
		this.departments = departments;
		this.photo = photo;
	}
	
	public TcKimlikNo getKimlik() {
		return kimlik;
	}
	public void setKimlik(TcKimlikNo kimlik) {
		this.kimlik = kimlik;
	}
	public FullName getFullname() {
		return fullname;
	}
	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}
	public Iban getIban() {
		return iban;
	}
	public void setIban(Iban iban) {
		this.iban = iban;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	public BirthYear getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}
	public JobStyle getStyle() {
		return style;
	}
	public void setStyle(JobStyle style) {
		this.style = style;
	}
	public List<Department> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	
}
