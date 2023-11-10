package employees.spring.service;

import java.util.List;

import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.EmployeeEntity;

public interface WorkTitleService {

	WorkTitleDto WorkTitle(WorkTitleDto workTitleDto);

	WorkTitleDto WorkTitle(String workTitle, WorkTitleDto workTitleDto);

	void removeWorkTitle(long EmployeeId);

	List<EmployeeEntity> getAllWorkTitles();
}
