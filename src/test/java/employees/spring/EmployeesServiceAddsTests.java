package employees.spring;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import employees.spring.dto.EmployeeDto;
import employees.spring.entity.EmployeeEntity;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.repo.EmployeeRepository;
import employees.spring.repo.WorkTitleRepository;
import employees.spring.service.EmployeesServiceImpl;

public class EmployeesServiceAddsTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private WorkTitleRepository workTitleRepository;

    @InjectMocks
    private EmployeesServiceImpl employeesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEmployee() {
    	
    	EmployeeDto employeeDto = new EmployeeDto(12345L, "", "Engineer", "John Doe");

        WorkTitleEntity workTitleEntity = new WorkTitleEntity();
        workTitleEntity.setWorkTitle("Engineer");

        EmployeeEntity employeeEntity = EmployeeEntity.of(employeeDto);
        employeeEntity.setWorkTitle(workTitleEntity);

        when(workTitleRepository.findById("Engineer")).thenReturn(Optional.of(workTitleEntity));
        when(employeeRepository.existsById(12345L)).thenReturn(false);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);

        EmployeeDto result = employeesService.addEmployee(employeeDto);

        assertNotNull(result);
        assertEquals(12345L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("Engineer", result.getWorkTitle());
    }

    @Test
    public void testAddEmployeeWithExistingId() {
        EmployeeDto employeeDto = new EmployeeDto(12345L, "", "Engineer", "John Doe");

        when(employeeRepository.existsById(12345L)).thenReturn(true);

        assertThrows(Exception.class, () -> {
            employeesService.addEmployee(employeeDto);
        });
    }


}
