package employees.spring.service;

import java.util.List;

import employees.spring.dto.EmployeeDto;
import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;

public interface EmployeesService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);

//	EmployeeDto updateEmployee(long employeeId, EmployeeDto employeeDto);
//
//	void removeEmployee(long eployeeId);

	List<EmployeeEntity> getAllEmployees();
	
	List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname();

	List<EmployeeEntity> findEmployeesByPattern(String patten);
	
	WorkTitleDto addWorkTitle(WorkTitleDto workTitleDto);

//	WorkTitleDto updateWorkTitle(String workTitle, WorkTitleDto workTitleDto);
//
//	void removeWorkTitle(String workTitle);

	List<WorkTitleEntity> getAllWorkTitlesEntities();
	List<String> getAllWorkTitles();

}
