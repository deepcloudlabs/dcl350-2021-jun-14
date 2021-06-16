package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.orm.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeEntityRepository repo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public boolean exists(Employee employee) {
		var kimlik = employee.getKimlik().getValue();
		return repo.existsById(kimlik );
	}

	@Override
	public Optional<Employee> findByIdentity(TcKimlikNo id) {
		var employee = repo.findById(id.getValue());
		if (employee.isEmpty())
			return Optional.empty();
		var managedEmployee = employee.get();
		var emp = modelMapper.map(managedEmployee, Employee.class);
		return Optional.of(emp);
	}

	@Override
	@Transactional
	public Employee insert(Employee employee) {
		if (repo.existsById(employee.getKimlik().getValue()))
			throw new IllegalArgumentException("Employee already exists");
		var employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
		repo.save(employeeEntity);
		return employee;
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		var managedEmployeeEntity = repo.findById(employee.getKimlik().getValue());
		if (managedEmployeeEntity.isEmpty())
			throw new IllegalArgumentException("Employee does not exist to update");			
		var emp = managedEmployeeEntity.get();
		var employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
		emp.setPhoto(employeeEntity.getPhoto());
		emp.setSalary(employeeEntity.getSalary());
		emp.setCurrency(employeeEntity.getCurrency());
		emp.setIban(employeeEntity.getIban());
		emp.setDepartments(employeeEntity.getDepartments());
		emp.setStyle(employeeEntity.getStyle());
		return employee;
	}

	@Override
	@Transactional
	public Optional<Employee> removeByIdentity(TcKimlikNo id) {
		var managedEmployeeEntity = repo.findById(id.getValue());
		if (managedEmployeeEntity.isEmpty())
			return Optional.empty();			
		var employeeEntity = managedEmployeeEntity.get();
		repo.delete(employeeEntity);
		return Optional.of(modelMapper.map(employeeEntity, Employee.class));
	}

}
