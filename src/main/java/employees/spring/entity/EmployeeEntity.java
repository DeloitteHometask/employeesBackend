package employees.spring.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import employees.spring.dto.EmployeeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="employees")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeEntity {
	
	@Id
	public String id;
	@Column(name="image_url")
	String imageUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="worktitles_id", nullable=true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	WorkTitleEntity workTitle;
	String name;
	
	
	public EmployeeDto build() {
		return new EmployeeDto(imageUrl, workTitle == null ? null : workTitle.getWorkTitle(), name);
	}
		
//	private String generateId(String name2) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public static EmployeeEntity of(EmployeeDto employeeDto) {
		return new EmployeeEntity(employeeDto.getId(), employeeDto.getImageUrl(), null, employeeDto.getName());
	}
	
}