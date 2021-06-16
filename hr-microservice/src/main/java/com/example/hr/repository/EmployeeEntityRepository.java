package com.example.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.orm.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String>  {
	List<EmployeeEntity> findAllByBirthYearBetween(int fromYear, int toYear);
	@Query("select e from EmployeeEntity e where e.birthYear between :fromYear and :toYear") // JPQL
	List<EmployeeEntity> yilAraliginaGoreGetir(int fromYear, int toYear);
	@Query(nativeQuery = true, 
		   value = "select e from employees e where e.byear between :fromYear and :toYear") // SQL
	List<EmployeeEntity> nativeYilAraliginaGoreGetir(int fromYear, int toYear);
	
	
}
