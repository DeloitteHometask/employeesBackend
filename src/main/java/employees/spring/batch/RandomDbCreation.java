package employees.spring.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import employees.spring.dto.EmployeeDto;
import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.service.EmployeesService;
import employees.spring.service.WorkTitleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RandomDbCreation {

	final EmployeesService employeesService;
	final WorkTitleService workTitleService;

	@Value("${app.random.employees.amount:15}")
	int nEmployees;
	@Value("${app.random.employees.initial-id:100000}")
	int initialEmployeeId;
	@Value("${app.random.workTitles.amount:5}")
	int nWorkTitles;
	@Value("#{'${app.random.workTitles}'.split(',')}")
	List<String> workTitleNames;
	@Value("#{'${app.random.employees.names.female}'.split(',')}")
	List<String> femaleNames;
	@Value("#{'${app.random.employees.names.male}'.split(',')}")
	List<String> maleNames;
	@Value("#{'${app.random.employees.surnames}'.split(',')}")
	List<String> surnames;
	@Value("#{'${app.random.employees.images.female}'.split(',')}")
	List<String> femaleImages;
	@Value("#{'${app.random.employees.images.male}'.split(',')}")
	List<String> maleImages;
	@Value("${app.random.creation.enable:false}")
	boolean creationEnable;
	int countErrors = 0;
	List<String> workTitles = new ArrayList<>();
	
	@PostConstruct
	void createDb() {
		if (creationEnable) {
			createWorkTitles();
			workTitles = workTitleService.getAllWorkTitles();
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
		workTitleNames.forEach(wt -> workTitleService.addWorkTitle(new WorkTitleDto(wt)));
	}

	private void createEmployees() {
		IntStream.rangeClosed(initialEmployeeId, initialEmployeeId + nEmployees)
				.mapToObj(index -> getRandomEmployee(index)).forEach(employee -> {
					try {
						employeesService.addEmployee(employee);
					} catch (Exception e) {
						log.error("error: {}", e);
						countErrors++;
					}
				});
	}

	private EmployeeDto getRandomEmployee(long index) {
		String gender = getRandomGender();
		String imageUrl = getRandomImageUrl(gender);
		String name = getRandomName(gender) + " " + getRandomObject(surnames);
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
		return getRandomNumber(0, 2) == 0 ? "male" : "female";
	}

	private int getRandomNumber(int min, int max) {
		return new Random().nextInt(min, max);
	}
}
