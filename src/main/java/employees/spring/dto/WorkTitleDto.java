package employees.spring.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WorkTitleDto {
	String workTitle;
//	WorkType type;
}