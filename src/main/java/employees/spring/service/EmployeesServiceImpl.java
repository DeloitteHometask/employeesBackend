package employees.spring.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employees.spring.dto.EmployeeDto;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.exceptions.NotFoundException;
import employees.spring.repo.EmployeeRepository;
import employees.spring.repo.WorkTitleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
 
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class EmployeesServiceImpl implements EmployeesService {

	final EmployeeRepository employeeRepository;
	final WorkTitleRepository workTitleRepository;

	@Value("${app.employee.id.min:100000}")
	long minId;
	@Value("${app.employee.id.max:999999}")
	long maxId;

	@Override
	@Transactional(readOnly = false)
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		if (employeeDto.getId() == null) {
			employeeDto.setId(getEmployeeUniqueId());
		}
		WorkTitleEntity workTitleEntity = null;
		String workTitle = employeeDto.getWorkTitle();
		if (workTitle != null) {
			workTitleEntity = workTitleRepository.findById(workTitle).orElseThrow(
					() -> (new NotFoundException(String.format("Worktitle %s does not exists", workTitle))));
		}
		EmployeeEntity employee = EmployeeEntity.of(employeeDto);
		employee.setWorkTitle(workTitleEntity);
		if (employeeRepository.existsById(employee.getId())) {
			throw new IllegalStateException("Employee with given id already exists" + employee.getId());
		}
		EmployeeDto res = employeeRepository.save(employee).build();
		log.debug("Employee added {}", res);
		return res;
	}
	
	private Long getEmployeeUniqueId() {
		return getUniqueId(employeeRepository);
	}

	private <T> Long getUniqueId(JpaRepository<T, Long> repository) {
		long res = 0;
		do {
			res = new Random().nextLong(minId, maxId + 1);
		} while (repository.existsById(res));
		return res;
	}
	
	
	@Override
	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> sortedEmployees = employeeRepository.findAll(); 
		return sortedEmployees;
	}
	
	@Override
	public List<EmployeeEntity> getAllEmployeesSorted(Sort sort) {
		List<EmployeeEntity> sortedEmployees = employeeRepository.findAll(sort); 
		return sortedEmployees;
	}
		
	@Override
	public List<EmployeeEntity> findEmployeesByPattern(String patten, int page, int size) {
		int offset = (page-1) * size;
		List<EmployeeEntity> receivedEmployees = employeeRepository.findEmployeesByPattern(patten, size, offset);
		log.debug("Amount of received employees by pattern {} : {}", patten, receivedEmployees.size());
        return receivedEmployees;
    }
}