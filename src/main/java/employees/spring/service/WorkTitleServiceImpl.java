package employees.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.exceptions.NotFoundException;
import employees.spring.repo.WorkTitleRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class WorkTitleServiceImpl implements WorkTitleService {

	final WorkTitleRepository workTitleRepository;
	final EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = false)
	public WorkTitleDto addWorkTitle(WorkTitleDto workTitleDto) {
		String id = workTitleDto.getWorkTitle();
		if (workTitleRepository.existsById(id)) {
			throw new IllegalArgumentException(String.format("WorkTitle with name %s already exists ", id));
		}
		WorkTitleEntity workTitle = WorkTitleEntity.of(workTitleDto);
		WorkTitleDto res = workTitleRepository.save(workTitle).build();
		log.debug("WorkTitle {} was added", res.getWorkTitle());
		return res;
	}

	@Override
	public List<WorkTitleEntity> getAllWorkTitlesEntities() {
		return workTitleRepository.findAll();
	}

	@Override
	public List<String> getAllWorkTitles() {
		return workTitleRepository.getAllWorkTitles();
	}
}
