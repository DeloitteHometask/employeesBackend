package employees.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import employees.spring.dto.EmployeeDto;
import employees.spring.dto.WorkTitleDto;
import employees.spring.dto.WorkType;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.service.EmployeesService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeesServiceAddsTests {
	private static final long ID_EMPLOYEE = 124l;
	private static final long ID_EMPLOYEE_NO_WORK_TITLE = 125l;
	private static final String WORK_TITLE = "Manager";
	private static final String WORK_TITLE_WRONG = "Manager1";

	@Autowired
	EmployeesService service;
	
//	WorkType.MANAGER
	WorkTitleDto workTitleDto = new WorkTitleDto("Manager");

	EmployeeDto employeeDto = new EmployeeDto(ID_EMPLOYEE, "", WORK_TITLE, "Bar Refaeli");
	EmployeeDto employeeDtoNoId = new EmployeeDto(null, "", WORK_TITLE, "Bar Refaeli");
	EmployeeDto employeeDtoNoWorkTitle = new EmployeeDto(ID_EMPLOYEE_NO_WORK_TITLE, "", null, "Bar Refaeli");
	EmployeeDto employeeDtoWrongWorkTitle = new EmployeeDto(ID_EMPLOYEE_NO_WORK_TITLE, "", WORK_TITLE_WRONG, "Bar Refaeli");

	@Test
	void contextLoads() {
	}

	@Test
//	@Disabled
	@Order(1)
	void addWorkTitle() {
		WorkTitleDto res = service.addWorkTitle(workTitleDto);
		assertEquals(workTitleDto.getWorkTitle(), res.getWorkTitle());
		//SECOND TIME
		assertThrowsExactly(IllegalStateException.class, () -> service.addWorkTitle(workTitleDto));
	}
	
	@Test
//	@Disabled
	@Order(2)
	void addEmployee() {
		EmployeeDto res = service.addEmployee(employeeDto);		
		assertEquals(ID_EMPLOYEE, res.getId());
		assertEquals(WORK_TITLE, res.getWorkTitle());
		//SECOND TIME
		assertThrowsExactly(IllegalStateException.class, () -> service.addEmployee(employeeDto));
		
//		assertThrowsExactly(Exception.class, ()-> service.addEmployee(employeeDtoWrongWorkTitle));
//		assertThrowsExactly(IllegalStateException.class, ()-> service.addEmployee(employeeDtoNoWorkTitle));


	}
}
