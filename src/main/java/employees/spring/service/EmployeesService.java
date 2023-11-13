package employees.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import employees.spring.dto.EmployeeDto;
import employees.spring.entity.EmployeeEntity;

public interface EmployeesService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);

	List<EmployeeEntity> getAllEmployees();
	List<EmployeeEntity> getAllEmployeesSorted(Sort sort);
	
	List<EmployeeEntity> findEmployeesByPattern(String patten, int page, int size);
	

}
