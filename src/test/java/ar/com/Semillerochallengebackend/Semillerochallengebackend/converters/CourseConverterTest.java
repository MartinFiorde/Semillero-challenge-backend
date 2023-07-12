package ar.com.Semillerochallengebackend.Semillerochallengebackend.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseStatus;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseTurn;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

// LSITA DE VIDEOS DE REFERENCIA (VIDEO 1 PARA REPOSITORIO. VIDEO 2 PARA SERVICIOS (APLICA ACA CON CIERTAS ADAPTACIONES)
// https://www.youtube.com/watch?v=tG-TwkIhjOk
@ExtendWith(MockitoExtension.class)
public class CourseConverterTest {

    @InjectMocks
    private CourseConverter courseConverter = new CourseConverter();
    
    @Test
    @DisplayName("It should turn CourseDTO into a valid Course Entity")
    public void testDtoToEntity() {
        // ARRANGE - Setting up the data that required for the test case
        Course expectedResult = new Course(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), CourseStatus.PROGRAMED, CourseTurn.MORNING, "curso inicial de java", null, null);
        CourseDTO dto = new CourseDTO(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);

        // ACT - Calling a Method/Unit that is being tested
        Course result = courseConverter.dtoToEntity(dto);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result);
        assertNotNull(expectedResult);
    }
}
