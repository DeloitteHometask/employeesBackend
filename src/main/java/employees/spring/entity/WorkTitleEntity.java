package employees.spring.entity;

import employees.spring.dto.WorkTitleDto;
import employees.spring.dto.WorkType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worktitles")
@Data
@NoArgsConstructor
@AllArgsConstructor
//(access = AccessLevel.PRIVATE)
public class WorkTitleEntity {
	@Id
	String workTitle;

	public WorkTitleDto build() {
		return new WorkTitleDto(workTitle);

	}
	
	static public WorkTitleEntity of(WorkTitleDto workTitleDto) {
		return new WorkTitleEntity(workTitleDto.getWorkTitle());

	}
}


//@Id
//String workTitle;
////@Enumerated(EnumType.STRING)
////WorkType type;
//
//
//public WorkTitleDto build() {
////	return new WorkTitleDto(workTitle, type);
//	return new WorkTitleDto(workTitle);
//
//}
//
//static public WorkTitleEntity of(WorkTitleDto workTitleDto) {
////	return new WorkTitleEntity(workTitleDto.getWorkTitle(), workTitleDto.getType());
//	return new WorkTitleEntity(workTitleDto.getWorkTitle());
//
//}