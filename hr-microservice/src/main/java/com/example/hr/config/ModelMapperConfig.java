package com.example.hr.config;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.orm.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static Converter<HireEmployeeRequest,Employee> hireEmployeeRequest2EmployeeConverter =
			context -> {
				var request = context.getSource();
				var kimlik = request.getIdentity();
				return new Employee.Builder(kimlik)
						           .fullname(request.getFirstName(), request.getLastName())
						           .iban(request.getIban())
						           .salary(request.getSalary(),request.getCurrency())
						           .photo(request.getPhoto().getBytes())
						           .birthYear(request.getBirthYear())
						           .departments(request.getDepartments().toArray(new String[0]))
						           .build();
			};
	private static Converter<EmployeeEntity,Employee> employeeEntity2EmployeeConverter =
			context -> {
				var employeeEntity = context.getSource();
				var kimlik = employeeEntity.getIdentity();
				return new Employee.Builder(kimlik)
						.fullname(employeeEntity.getFirstName(), employeeEntity.getLastName())
						.iban(employeeEntity.getIban())
						.salary(employeeEntity.getSalary(),employeeEntity.getCurrency())
						.photo(employeeEntity.getPhoto())
						.birthYear(employeeEntity.getBirthYear())
						.departments(employeeEntity.getDepartments().toArray(new String[0]))
						.build();
			};
	private static Converter<Employee,EmployeeEntity> employee2EmployeeEntityConverter =
			context -> {
				var employee = context.getSource();
				var employeeEntity = new EmployeeEntity();
				employeeEntity.setBirthYear(employee.getBirthYear().getValue());
				employeeEntity.setFirstName(employee.getFullname().getFirstName());
				employeeEntity.setLastName(employee.getFullname().getLastName());
				employeeEntity.setSalary(employee.getSalary().getValue());
				employeeEntity.setCurrency(employee.getSalary().getCurrency());
				employeeEntity.setIban(employee.getIban().getValue());
				employeeEntity.setDepartments(employee.getDepartments().stream().map(Department::name).collect(Collectors.toList()));
				employeeEntity.setStyle(employee.getStyle());
				employeeEntity.setPhoto(employee.getPhoto().getValue());
				return employeeEntity;
			};			
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper(); // Spring Bean -> DI -> @Autowired
		mapper.addConverter(hireEmployeeRequest2EmployeeConverter, HireEmployeeRequest.class, Employee.class);
		mapper.addConverter(employeeEntity2EmployeeConverter, EmployeeEntity.class, Employee.class);
		mapper.addConverter(employee2EmployeeEntityConverter, Employee.class, EmployeeEntity.class);
		return mapper;
	}
}
