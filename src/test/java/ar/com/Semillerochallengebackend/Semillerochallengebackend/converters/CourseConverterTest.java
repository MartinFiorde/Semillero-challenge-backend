package ar.com.Semillerochallengebackend.Semillerochallengebackend.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.CourseDTO;
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
    private CourseConverter courseConverter = new CourseConverter(new ModelMapper());
    
    @Test
    @DisplayName("It should turn CourseDTO into a valid Course Entity")
    public void testDtoToEntity() {
        System.out.println("dtoToEntity");

        // ARRANGE - Setting up the data that required for the test case
        CourseDTO dto = new CourseDTO(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);

        // ACT - Calling a Method/Unit that is being tested
        Course result = courseConverter.dtoToEntity(dto);

        // ASSERT - Verify that the expected result is correct or not
        Course expectedResult = new Course(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), CourseStatus.PROGRAMED, CourseTurn.MORNING, "curso inicial de java", null, null);
        assertEquals(expectedResult, result);
        assertNotNull(expectedResult);
        //assertEquals(1, list.size());
    }
}
