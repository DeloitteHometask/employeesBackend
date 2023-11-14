package employees.spring.entity;

import java.io.Serializable;

import employees.spring.dto.WorkTitleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "worktitles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkTitleEntity implements Serializable {
	private static final long serialVersionUID = 7255108790636917400L;
	@Id
	String workTitle;

	public WorkTitleDto build() {
		return new WorkTitleDto(workTitle);

	}
	
	static public WorkTitleEntity of(WorkTitleDto workTitleDto) {
		return new WorkTitleEntity(workTitleDto.getWorkTitle());

	}
}