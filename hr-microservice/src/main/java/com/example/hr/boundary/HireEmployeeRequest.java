package com.example.hr.boundary;

import java.util.List;

import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HireEmployeeRequest {
	@JsonProperty("kimlik")
	private String identity;
	@JsonProperty("first")
	private String firstName;
	@JsonProperty("last")
	private String lastName;
	private String iban;
	private double salary;
	private FiatCurrency currency;
	@JsonProperty("birth_year")
	private int birthYear;
	private JobStyle style;
	private List<String> departments;
	private String photo;

	public HireEmployeeRequest() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public JobStyle getStyle() {
		return style;
	}

	public void setStyle(JobStyle style) {
		this.style = style;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "HireEmployeeRequest [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", salary=" + salary + ", currency=" + currency + ", birthYear=" + birthYear
				+ ", style=" + style + ", departments=" + departments + "]";
	}

}
