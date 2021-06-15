package com.example.exercise;

import com.example.hr.domain.Employee;

public class CreateEmployee {

	public static void main(String[] args) {
		// new Employee("1111111110","jack","bauer","tr12345",1986,4000);
		var jack = new Employee.Builder("11111111110")
				               .fullname("jack", "bauer")
				               .birthYear(1986)
				               .iban("AD1200012030200359100100")
				               .salary(4000)
				               .departments("IT", "SALES")
				               .photo("a".getBytes())
				               .style("FULL_TIME")
				               .build();	
		System.err.println("Hello, "+jack.getFullname());
	}

}
