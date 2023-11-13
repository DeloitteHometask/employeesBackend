package employees.spring.service;

import java.util.List;

import employees.spring.dto.EmployeeDto;
import employees.spring.entity.EmployeeEntity;

public interface EmployeesService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);

//	EmployeeDto updateEmployee(long employeeId, EmployeeDto employeeDto);
//
//	void removeEmployee(long eployeeId);

	List<EmployeeEntity> getAllEmployees();
	
	List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname();

	List<EmployeeEntity> findEmployeesByPattern(String patten, int page);
	

}
