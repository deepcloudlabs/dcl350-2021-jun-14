package com.example.hr.orm;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

@Entity // JPA's Entity
@Table(name = "employees")
public class EmployeeEntity {
	@Column(name = "kimlik")
	@Id
	private String identity;
	@Column(name = "first")
	private String firstName;
	@Column(name = "last")
	private String lastName;
	private String iban;
	private double salary;
	@Enumerated(EnumType.STRING)
	private FiatCurrency currency;
	@Column(name = "byear")
	private int birthYear;
	@Enumerated(EnumType.STRING)
	private JobStyle style;
	@ElementCollection
	private List<String> departments;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;

	public EmployeeEntity() {
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", salary=" + salary + ", currency=" + currency + ", birthYear=" + birthYear
				+ ", style=" + style + ", departments=" + departments + "]";
	}

}
