package employees.spring.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employees.spring.dto.EmployeeDto;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
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
					() -> (new IllegalArgumentException(String.format("Worktitle %s does not exists", workTitle))));
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
	
	
	
//	public SubjectDto addSubject(SubjectDto subjectDto) {
//		String id = subjectDto.getId();
//		if (subjectRepository.existsById(id)) {
//			throw new IllegalStateException(String.format("Subject with id %s already exists ", id));// код 400
//		}
//		Lecturer lecturer = null;
//		Long lecturerId = subjectDto.getLecturerId();
//		if (lecturerId != null) {
//			lecturer = lecturerRepository.findById(lecturerId).orElseThrow(
//					() -> (new NotFoundException(String.format("Lecturer with id %d does not exists", lecturerId))));
//		}
//		Subject subjectEntity = Subject.of(subjectDto);
//		subjectEntity.setLecturer(lecturer);
//		SubjectDto res = subjectRepository.save(subjectEntity).build();
//		log.debug("Subject added {}", res);
//		return res;
//	}

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

//	@Override
//	public EmployeeDto updateEmployee(long EmployeeId, EmployeeDto employeeDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	@Transactional(readOnly = false)
//	public void removeEmployee(long employeeId) {
//		Employee employeeForRemove = employeeRepository.findById(lecturerId).orElseThrow(
//				() -> new NotFoundException(String.format("Lecturer with id %s does not exist", lecturerId)));
//		lecturerRepository.delete(lecturerForRemove);
//		return lecturerForRemove.build();
//	}

	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname() {
		return employeeRepository.getAllEmployeesSortedByFirstLettersOfNameSurname();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByPattern(String patten) {
		return employeeRepository.findEmployeesByPattern(patten);
	}

	




}
