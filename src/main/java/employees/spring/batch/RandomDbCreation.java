package employees.spring.batch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import employees.spring.dto.EmployeeDto;
import employees.spring.dto.WorkTitleDto;
import employees.spring.dto.WorkType;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.repo.EmployeeRepository;
import employees.spring.repo.WorkTitleRepository;
import employees.spring.service.EmployeesService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomDbCreation {

	final EmployeesService service;

	@Value("${app.random.employees.amount:100}")
	int nEmployees;
	@Value("${app.random.employees.initial-id:100000}")
	int initialEmployeeId;
	@Value("${app.random.workTitles.amount:5}")
	int nWorkTitles;
//	@Value("${app.random.employees.names.female:}")
	@Value("#{'${app.random.employees.names.female:Alice,Bob,Charlie,Diana,Emily,Fiona,Grace,Holly,Ivy,Julia}'.split(',')}")
	List<String> femaleNames;
	@Value("#{'${app.random.employees.names.male:Adam,Benjamin,Charlie,David,Ethan,Frank,George,Henry,Isaac,Jack}'.split(',')}")
	List<String> maleNames;
	@Value("#{'${app.random.employees.surnames:Smith,Johnson,Williams,Jones,Brown,Davis,Miller,Wilson,Moore,Taylor,Anderson,Thomas,Jackson,White,Harris}'.split(',')}")
	List<String> surnames;
	@Value("#{'${app.random.employees.images.female:http,http,http,http}'.split(',')}")
	List<String> femaleImages;
	@Value("#{'${app.random.employees.images.male:http,http,http,http}'.split(',')}")
	List<String> maleImages;
	@Value("${app.random.creation.enable:false}")
	boolean creationEnable;
	int countErrors = 0;
	List<String> workTitles = new ArrayList<>();

	@PostConstruct
	void createDb() {
		if (creationEnable) {
			
			createWorkTitles();
			workTitles = service.getAllWorkTitles();
			if (workTitles.size() > 0) {
				createEmployees();
			}

			if (countErrors > 0) {
				log.warn("DB created with {} exceptions", countErrors);
			} else {
				log.debug("DB created with no logs");
			}
		} else {
			log.debug("DB has not been created");
		}
	}

	private void createWorkTitles() {
		IntStream.rangeClosed(1, nWorkTitles).mapToObj(i -> 
				getRandomWorkTitleEntity(i)).forEach(workTitle -> {
			try {
				service.addWorkTitle(workTitle);
			} catch (Exception e) {
				log.error("error: {}", e);
				countErrors++;
			}
		});
	}

	private WorkTitleDto getRandomWorkTitleEntity(int index) {
		String name = "workTitle" + index;
		WorkType workType = randomSubjectType();
//		return new WorkTitleDto(name, workType);
		return new WorkTitleDto(name);

	}

	private WorkType randomSubjectType() {
		WorkType[] values = WorkType.values();
		int index = getRandomNumber(0, values.length);
		return values[index];
	}

	private void createEmployees() {
		IntStream.rangeClosed(initialEmployeeId, initialEmployeeId + nEmployees)
				.mapToObj(index -> getRandomEmployee(index)).forEach(employee -> {
					try {
						service.addEmployee(employee);
					} catch (Exception e) {
						log.error("error: {}", e);
						countErrors++;
					}
				});
	}

	private EmployeeDto getRandomEmployee(long index) {
		String gender = getRandomGender();
		String imageUrl = getRandomImageUrl(gender);
		String name = getRandomName(gender) + getRandomObject(surnames);
		WorkTitleEntity workTitle = new WorkTitleEntity(getRandomObject(workTitles));
		return new EmployeeDto(index, imageUrl, workTitle.getWorkTitle(), name);
	}

	private String getRandomImageUrl(String gender) {
		return gender == "male" ? getRandomObject(maleImages) : getRandomObject(femaleImages);
	}

	private String getRandomName(String gender) {
		return gender == "male" ? getRandomObject(maleNames) : getRandomObject(femaleNames);
	}

	private String getRandomObject(List<String> list) {
		return list.get(getRandomNumber(0, list.size()));
	}

	private String getRandomGender() {
		return getRandomNumber(0, 2) == 0 ? "Male" : "Female";
	}

	private int getRandomNumber(int min, int max) {
		return new Random().nextInt(min, max);
	}
}
