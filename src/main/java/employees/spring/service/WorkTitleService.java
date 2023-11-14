package employees.spring.service;

import java.util.List;

import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.WorkTitleEntity;

public interface WorkTitleService {

	WorkTitleDto addWorkTitle(WorkTitleDto workTitleDto);
	List<WorkTitleEntity> getAllWorkTitlesEntities();
	List<String> getAllWorkTitles();
}
