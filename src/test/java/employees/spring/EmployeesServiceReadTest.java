package employees.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.repo.EmployeeRepository;
import employees.spring.repo.WorkTitleRepository;
import employees.spring.service.EmployeesService;
import employees.spring.service.WorkTitleService;

@SpringBootTest
@Sql(scripts = { "employees-read-test-script.sql" })

class EmployeesServiceReadTest {
	@Autowired
	EmployeesService employeesService;
	@Autowired
	WorkTitleService workTitleService;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	WorkTitleRepository workTitleRepository;

	@Test
	void allEmployeesTest() {
		List<EmployeeEntity> actual = employeesService.getAllEmployees();
		assertEquals(4, actual.size());
		assertEquals(126, actual.get(0).getId());
		assertEquals("Eyal Golan", actual.get(0).getName());
		assertEquals(127, actual.get(1).getId());
		assertEquals("Bar Refaeli", actual.get(1).getName());
	}

	@Test
	void allWorkTitlesTest() {
		List<String> actualTitles = workTitleService.getAllWorkTitles();
		assertEquals(4, actualTitles.size());
		assertEquals("Designer", actualTitles.get(0));
		assertEquals("Senior Manager", actualTitles.get(3));

		List<WorkTitleEntity> actualEntities = workTitleService.getAllWorkTitlesEntities();
		assertEquals(4, actualEntities.size());
		assertEquals("Designer", actualEntities.get(0).getWorkTitle());
	}

	@Test
	void sortedEmployeesTest() {
		List<EmployeeEntity> employees = employeesService.getAllEmployeesSorted(Sort.by("name"));
		assertEquals(4, employees.size());
		assertEquals(127, employees.get(0).getId());
		assertEquals(126, employees.get(1).getId());
		assertEquals(128, employees.get(2).getId());
		assertEquals(129, employees.get(3).getId());

		assertEquals("Bar Refaeli", employees.get(0).getName());
		assertEquals("Eyal Golan", employees.get(1).getName());
		assertEquals("Maor Edri", employees.get(2).getName());
		assertEquals("Maya Boskila", employees.get(3).getName());
	}

	@Test
	void findEmployeesByPatternTest() {
		int page = 1;
		int pageSize = 10;
		List<EmployeeEntity> employees = employeesService.findEmployeesByPattern("Ma", page, pageSize);
		assertEquals(3, employees.size());
		assertEquals("Eyal Golan", employees.get(0).getName());
		assertEquals("Senior Manager", employees.get(0).getWorkTitle().getWorkTitle());
		assertEquals("Maor Edri", employees.get(1).getName());
		assertEquals("Maya Boskila", employees.get(2).getName());

	}

}
