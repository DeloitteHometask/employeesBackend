package employees.spring.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employees.spring.dto.EmployeeDto;
import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
@Validated
@CrossOrigin
//@Slf4j
public class EmployeeController {

	private final EmployeesService service;

	@PostMapping("/employees")
	public EmployeeDto addEmployee(@RequestBody EmployeeDto employee) {
		EmployeeDto res = service.addEmployee(employee);
//		log.debug("Employee with id {} was added", res.getId());
		return res;
	}

	@PostMapping("/worktitles")
	public WorkTitleDto addworkTitle(@RequestBody WorkTitleDto workTitle) {
		WorkTitleDto res = service.addWorkTitle(workTitle);
//		log.debug("WorkTitle {} was added", res.getWorkTitle());
		return res;
	}

	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployees() {
		return service.getAllEmployees();
	}
	
	@GetMapping("/employees/sorted")
	public List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname() {
		return service.getAllEmployeesSortedByFirstLettersOfNameSurname();
	}

	@GetMapping("/employees/sorted/{pattern}")
	public List<EmployeeEntity> getAllEmployeesSortByPattern(@PathVariable String pattern) {
		return service.findEmployeesByPattern(pattern);
	}

	@GetMapping("/worktitles")
	public List<WorkTitleEntity> getAllWorkTitles() {
		return service.getAllWorkTitlesEntities();
	}
}