package employees.spring.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import employees.spring.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	
	List<EmployeeEntity> findAll(Sort sort);
	
	@Query(value = "SELECT * FROM employees e "
			+ "LEFT JOIN worktitles wt ON e.worktitles_id = wt.work_title "
			+ "WHERE (LOWER(e.name) LIKE CONCAT(LOWER(:pattern), '%') "
			+ "OR LOWER(e.name) LIKE CONCAT('% ', LOWER(:pattern), '%') "
			+ "OR LOWER(wt.work_title) LIKE CONCAT(LOWER(:pattern), '%') "
			+ "OR LOWER(wt.work_title) LIKE CONCAT('% ', LOWER(:pattern), '%')) "
			+ "ORDER BY e.name ASC "
			+ "LIMIT :limit "
			+ "OFFSET :offset", nativeQuery = true)
	List<EmployeeEntity> findEmployeesByPattern(@Param("pattern") String pattern, int limit, int offset);


}

