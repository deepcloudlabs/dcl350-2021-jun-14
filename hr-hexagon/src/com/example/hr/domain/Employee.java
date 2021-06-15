package com.example.hr.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

// DDD: Bounded Context, Ubiquitous Language Entity Class
// 1) Have identity
// 2) Mutable Class
// Entity Root -> at the root of Object Graph -> Aggregate -> Entity
@BoundedContext(name = "hr", subdomain = "hr") // meta-data -> just lives in the source code
@EntityClass(identities = "kimlik", aggregate = true)
// Effective Java 3 ed
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

	public Employee(Builder builder) {
		this.kimlik = builder.kimlik;
		this.fullname = builder.fullname;
		this.iban = builder.iban;
		this.salary = builder.salary;
		this.birthYear = builder.birthYear;
		this.style = builder.style;
		this.departments = builder.departments;
		this.photo = builder.photo;
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

	// Flow API
	public static class Builder {
		private TcKimlikNo kimlik;
		private FullName fullname;
		private Iban iban;
		private Salary salary;
		private BirthYear birthYear;
		private JobStyle style;
		private List<Department> departments; // multiplicity -> [], List
		private Photo photo;

		public Builder(String kimlik) {
			this.kimlik = TcKimlikNo.valueOf(kimlik);
		}

		public Builder fullname(String firstName, String lastName) {
			this.fullname = FullName.of(firstName, lastName);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Salary.of(value, currency);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Salary.of(value, FiatCurrency.TL);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.of(value);
			return this;
		}

		public Builder style(String style) {
			this.style = JobStyle.valueOf(style);
			return this;
		}

		public Builder photo(byte[] value) {
			this.photo = Photo.valueOf(value);
			return this;
		}

		public Builder departments(String... departments) {
			this.departments = Arrays.stream(departments).map(Department::valueOf).collect(toList());
			return this;
		}

		public Employee build() { // (1)
			// validation/business rule/invariants/conditions
			if (this.departments.stream().anyMatch(department -> department.equals(Department.IT))
					&& this.salary.getValue() < 5_000)
				throw new IllegalStateException("Employee in IT must have salary larger than 5000");
			return new Employee(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kimlik == null) ? 0 : kimlik.hashCode());
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
		Employee other = (Employee) obj;
		if (kimlik == null) {
			if (other.kimlik != null)
				return false;
		} else if (!kimlik.equals(other.kimlik))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [kimlik=" + kimlik + ", fullname=" + fullname + ", iban=" + iban + ", salary=" + salary
				+ ", birthYear=" + birthYear + ", style=" + style + ", departments=" + departments + "]";
	}

}
