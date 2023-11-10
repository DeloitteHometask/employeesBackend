package employees.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import employees.spring.dto.WorkType;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.repo.EmployeeRepository;
import employees.spring.repo.WorkTitleRepository;
import employees.spring.service.EmployeesService;
@SpringBootTest
@Sql(scripts = {"employees-read-test-script.sql"})

class EmployeesServiceReadTest {
	@Autowired
	EmployeesService service;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	WorkTitleRepository workTitleRepository;

//	insert into employees (id, image_url, worktitles_id, name) values(123, '', 'Designer', 'Rivka Malkovich');
//	insert into employees (id, image_url, worktitles_id, name) values(124, '', 'Manager', 'Sara Freedman');
//	insert into employees (id, image_url, worktitles_id, name) values(125, '', 'Programmer', 'Yosef Lubovich');
	
	
	@Test
	@Disabled
	void allEmployeesTest() {
		List<EmployeeEntity> actual = service.getAllEmployees();
		assertEquals(4, actual.size());
		assertEquals(126, actual.get(0).getId());
		assertEquals("Eyal Golan", actual.get(0).getName());
		assertEquals(127, actual.get(1).getId());
		assertEquals("Bar Refaeli", actual.get(1).getName());
	}
	
	@Test
	@Disabled
	void allWorkTitlesTest() {
		List<String> actualTitles = service.getAllWorkTitles();
		assertEquals(4, actualTitles.size());
		assertEquals("Designer", actualTitles.get(0));
		assertEquals("Senior Manager", actualTitles.get(3));

		List<WorkTitleEntity> actualEntities = service.getAllWorkTitlesEntities();
		assertEquals(4, actualEntities.size());
		assertEquals("Designer", actualEntities.get(0).getWorkTitle());
//		assertEquals(WorkType.MANAGER, actualEntities.get(0).getType());
	}
	
	@Test
	void sortedEmployeesTest() {
		List<EmployeeEntity> employees = service.getAllEmployeesSortedByFirstLettersOfNameSurname();
		assertEquals(4, employees.size());
		assertEquals(127, employees.get(0).getId());
		assertEquals(126, employees.get(1).getId());
		assertEquals(129, employees.get(2).getId());
		assertEquals(128, employees.get(3).getId());

		
		
		//		List<StudentMark> studentsMarks = service.studentsAvgMarks();
//		assertEquals(5, studentsMarks.size());
//		assertEquals(127, studentsMarks.get(0).getId());
//		assertEquals("Rivka", studentsMarks.get(0).getName());
//		assertEquals(100, studentsMarks.get(0).getAvgmark());
//		
//		assertEquals(126, studentsMarks.get(4).getId());
//		assertEquals("David", studentsMarks.get(4).getName());
//		assertEquals(0, studentsMarks.get(4).getAvgmark());
	}
	
	@Test
	@Disabled
	void filteredSortedEmployeesTest() {
//		List<StudentMark> studentsMarks = service.studentsAvgMarks();
//		assertEquals(5, studentsMarks.size());
//		assertEquals(127, studentsMarks.get(0).getId());
//		assertEquals("Rivka", studentsMarks.get(0).getName());
//		assertEquals(100, studentsMarks.get(0).getAvgmark());
//		
//		assertEquals(126, studentsMarks.get(4).getId());
//		assertEquals("David", studentsMarks.get(4).getName());
//		assertEquals(0, studentsMarks.get(4).getAvgmark());
	}
	
	
	
//	@Test
//	void fetchLecturerNoTransactionTest() {
//		Subject subject = subjectRepository.findById("S3").get();
//		assertThrows(Exception.class, ()-> subject.getLecturer().getName());
//
//	}
//	
//	@Test
//	void fetchMarksNoTransactionTest() {
//		Student student= studentRepository.findById(123l).get();
//		assertThrows(Exception.class, ()-> student.getMarks().size());
//	}
//	
//	@Test
//	@Transactional(readOnly = true)
//	void fetchLecturerTest() {
//		Subject subject = subjectRepository.findById("S3").get();
//		assertEquals(421, subject.getLecturer().getId());
//		assertEquals("Sara Abramovna", subject.getLecturer().getName());//в сабдж нет имени лектора но есть джойн
//	}
//	
//	@Test
//	@Transactional(readOnly = true)
//	void fetchMarksTest() {
//		Student student= studentRepository.findById(123l).get();
//		assertEquals(4, student.getMarks().size());
//	}
}
