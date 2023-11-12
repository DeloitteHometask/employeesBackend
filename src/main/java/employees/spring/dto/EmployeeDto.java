package employees.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeDto {
	Long id;
	String imageUrl;
	String workTitle;
	String name;
}