package com.example.hr.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.domain.Employee;

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
	@Bean
	public ModelMapper modelMapper() {
		var mapper = new ModelMapper(); // Spring Bean -> DI -> @Autowired
		mapper.addConverter(hireEmployeeRequest2EmployeeConverter, HireEmployeeRequest.class, Employee.class);
		return mapper;
	}
}
