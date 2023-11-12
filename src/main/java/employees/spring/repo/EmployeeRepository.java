package employees.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import employees.spring.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	@Query(value = "SELECT * FROM employees " + "ORDER BY " + "CASE WHEN name LIKE '% %' "
			+ "THEN SUBSTRING(name, 1, POSITION(' ', name) - 1) " + "ELSE name END, " + "CASE WHEN name LIKE '% %' "
			+ "THEN SUBSTRING(name, POSITION(' ', name) + 1) " + "ELSE '' END", nativeQuery = true)
	List<EmployeeEntity> getAllEmployeesSortedByFirstLettersOfNameSurname();

//	@Query(value = "SELECT * FROM employees " + "ORDER BY CASE "
//			+ "  WHEN SUBSTRING(name, 1, 1) = :pattern OR SUBSTRING(worktitles_id, 1, 1) = :pattern THEN 0 " + "  ELSE 1 "
//			+ "END", nativeQuery = true)

//	@Query("SELECT e FROM employees e WHERE e.name LIKE :pattern "
//			+ "OR e.workTitle.workTitle LIKE :pattern% " + "ORDER BY CASE " + "WHEN e.name LIKE :pattern% THEN 0 "
//			+ "WHEN e.workTitle.workTitle LIKE :pattern% THEN 1 " + "ELSE 2 END")

//	@Query(value = "SELECT e FROM employees e "
//			+ "LEFT JOIN worktitles wt "
//			+ "WHERE ((LOWER(SUBSTRING(e.name, 1, 1)) LIKE LOWER(CONCAT('%', :pattern, '%'))) "
//			+ "OR (LOWER(SUBSTRING(e.name, 2, 1)) LIKE LOWER(CONCAT('%', :pattern, '%')))) "
//			+ "OR ((LOWER(SUBSTRING(wt.workTitle, 1, 1)) LIKE LOWER(CONCAT('%', :pattern, '%'))) "
//			+ "OR (LOWER(SUBSTRING(wt.workTitle, 2, 1)) LIKE LOWER(CONCAT('%', :pattern, '%'))))", nativeQuery = true)
	
	
	@Query(value = "SELECT * FROM employees e "
			+ "LEFT JOIN worktitles wt ON e.worktitles_id = wt.work_title "
			+ "WHERE (LOWER(e.name) LIKE CONCAT(LOWER(:pattern), '%') "
			+ "OR LOWER(e.name) LIKE CONCAT('% ', LOWER(:pattern), '%') "
			+ "OR LOWER(wt.work_title) LIKE CONCAT(LOWER(:pattern), '%') "
			+ "OR LOWER(wt.work_title) LIKE CONCAT('% ', LOWER(:pattern), '%'))", nativeQuery = true)
	List<EmployeeEntity> findEmployeesByPattern(@Param("pattern") String pattern);

	
//	"WHERE (LOWER(SUBSTRING(e.name, 1, 1)) LIKE LOWER(CONCAT('%', :pattern, '%')) "
//    + "OR LOWER(SUBSTRING(e.name, 2, 1)) LIKE LOWER(CONCAT('%', :pattern, '%'))) "
//    + "OR (LOWER(SUBSTRING(wt.work_title, 1, 1)) LIKE LOWER(CONCAT('%', :pattern, '%')) "
//    + "OR LOWER(SUBSTRING(wt.work_title, 2, 1)) LIKE LOWER(CONCAT('%', :pattern, '%')))"
//	"SELECT e FROM Employees e "
//	+ "LEFT JOIN e.workTitle wt "
//	+ "WHERE (LOWER(e.name) LIKE LOWER(CONCAT('%', :pattern, '%'))) "
//	+ "OR (LOWER(wt.workTitle) LIKE LOWER(CONCAT('%', :pattern, '%')))"
//	@Query(value = EMPLOYEES + "group by employees.id order by avg(mark) desc limit :nStudents", nativeQuery = true)
//	List<IdName> findBestStudentsLecturer(long lecturerId, int nStudents);

//    List<EmployeeEntity> findByWorkTitleContainingIgnoreCaseOrNameContainingIgnoreCase(String workTitle, String name);

}

//@Query(value = EMPLOYEES + "group by employees.id order by *** :pattern", nativeQuery = true)
//List<Employee> sortEmployeesByPattern(String pattern);

//@Query("SELECT e FROM Employee e " +
//           "WHERE e.name LIKE '%'  :searchString  '%' " +
//           "OR e.position LIKE '%'  :searchString  '%' " +
//           "ORDER BY " +
//           "(LENGTH(e.name) - LENGTH(REPLACE(LOWER(e.name), LOWER(:searchString), ''))) / LENGTH(:searchString) DESC, " +
//           "(LENGTH(e.position) - LENGTH(REPLACE(LOWER(e.position), LOWER(:searchString), ''))) / LENGTH(:searchString) DESC")

//@Query("SELECT e FROM Employee e " +
//"WHERE e.name LIKE '%'  :searchString  '%' " +
//"OR e.position LIKE '%'  :searchString  '%' " +
//"ORDER BY " +
//"(LENGTH(e.name) - LENGTH(REPLACE(LOWER(e.name), LOWER(:searchString), ''))) / LENGTH(:searchString) DESC, " +
//"(LENGTH(e.position) - LENGTH(REPLACE(LOWER(e.position), LOWER(:searchString), ''))) / LENGTH(:searchString) DESC")