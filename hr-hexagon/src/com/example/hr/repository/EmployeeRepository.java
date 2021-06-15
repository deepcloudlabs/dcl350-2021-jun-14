package com.example.hr.repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository extends CrudRepository<Employee,TcKimlikNo> {
	
}
