package employees.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import employees.spring.entity.WorkTitleEntity;

public interface WorkTitleRepository extends JpaRepository<WorkTitleEntity, String> {

	@Query(value = "SELECT work_title FROM worktitles", nativeQuery = true)
	List<String> getAllWorkTitles();
	
	
}
