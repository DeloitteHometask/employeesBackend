package employees.spring.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.service.WorkTitleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@Validated
@CrossOrigin
//@Slf4j
public class WorkTitlesController {

	private final WorkTitleService service;

	@PostMapping("/worktitles")
	public WorkTitleDto addworkTitle(@RequestBody WorkTitleDto workTitle) {
		WorkTitleDto res = service.addWorkTitle(workTitle);
//		log.debug("WorkTitle {} was added", res.getWorkTitle());
		return res;
	}

	@GetMapping("/worktitles")
	public List<WorkTitleEntity> getAllWorkTitles() {
		return service.getAllWorkTitlesEntities();
	}


//	List<AutocompleteItem> items = service.search(query);
//    return ResponseEntity.ok(items);
}