package employees.spring.dto;

import employees.spring.entity.WorkTitleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeDto {
	String imageUrl;
	String workTitle;
	String name;
}