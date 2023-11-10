package employees.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import employees.spring.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

//	@Query(value = EMPLOYEES + "group by employees.id order by *** :pattern", nativeQuery = true)
//	List<Employee> sortEmployeesByPattern(String pattern);
	
	@Query(value = "SELECT * FROM employees "
	        + "ORDER BY "
	        + "CASE WHEN name LIKE '% %' "
	        + "THEN SUBSTRING(name, 1, POSITION(' ', name) - 1) "
	        + "ELSE name END, "
	        + "CASE WHEN name LIKE '% %' "
	        + "THEN SUBSTRING(name, POSITION(' ', name) + 1) "
	        + "ELSE '' END", nativeQuery = true)
	List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname();

	@Query(value = "SELECT * FROM employees " + "ORDER BY CASE "
			+ "  WHEN SUBSTRING(name, 1, 1) = :pattern OR SUBSTRING(worktitles_id, 1, 1) = :pattern THEN 0 " + "  ELSE 1 "
			+ "END", nativeQuery = true)
	List<EmployeeEntity> findEmployeesByPattern(@Param("pattern") String pattern);

//	@Query(value = EMPLOYEES + "group by employees.id order by avg(mark) desc limit :nStudents", nativeQuery = true)
//	List<IdName> findBestStudentsLecturer(long lecturerId, int nStudents);

}
