package employees.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import employees.spring.dto.WorkTitleDto;
import employees.spring.entity.WorkTitleEntity;
import employees.spring.repo.WorkTitleRepository;
import employees.spring.service.WorkTitleServiceImpl;

public class WorkTitleServiceAddsTests {

    @Mock
    private WorkTitleRepository workTitleRepository;

    @InjectMocks
    private WorkTitleServiceImpl workTitleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddWorkTitle() {
        WorkTitleDto workTitleDto = new WorkTitleDto("Engineer");

        WorkTitleEntity workTitleEntity = WorkTitleEntity.of(workTitleDto);

        when(workTitleRepository.existsById("Engineer")).thenReturn(false);
        when(workTitleRepository.save(workTitleEntity)).thenReturn(workTitleEntity);

        WorkTitleDto result = workTitleService.addWorkTitle(workTitleDto);

        assertEquals("Engineer", result.getWorkTitle());
    }

    @Test
    public void testAddExistingWorkTitle() {
        WorkTitleDto workTitleDto = new WorkTitleDto("Engineer");

        when(workTitleRepository.existsById("Engineer")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            workTitleService.addWorkTitle(workTitleDto);
        });
    }
}
