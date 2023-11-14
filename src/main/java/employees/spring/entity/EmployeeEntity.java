package employees.spring.entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import employees.spring.dto.EmployeeDto;

import jakarta.persistence.*;

//import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "employees", indexes = { @Index(name = "name", columnList = "name") })
@AllArgsConstructor()
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = -8779054357165240355L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	@Column(name = "image_url")
	String imageUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "worktitles_id", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	WorkTitleEntity workTitle;

	@Column(name = "name")
	String name;

	public EmployeeDto build() {
		return new EmployeeDto(id, imageUrl, workTitle == null ? null : workTitle.getWorkTitle(), name);
	}

	public static EmployeeEntity of(EmployeeDto employeeDto) {
		return new EmployeeEntity(employeeDto.getId(), employeeDto.getImageUrl(), null, employeeDto.getName());
	}

}