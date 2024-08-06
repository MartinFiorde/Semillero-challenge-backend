package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseStatusEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseTurnEnum;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

// LSITA DE VIDEOS DE REFERENCIA (VIDEO 1 PARA REPOSITORIO. VIDEO 2 PARA SERVICIOS (APLICA ACA CON CIERTAS ADAPTACIONES)
// https://www.youtube.com/watch?v=tG-TwkIhjOk
@ExtendWith(MockitoExtension.class)
class CourseConverterTest {

    @InjectMocks
    private CourseConverter courseConverter;
    
    @Test
    @DisplayName("It should turn CourseDTO into a valid Course Entity")
    void testDtoToEntity() {
        // ARRANGE - Setting up the data that required for the test case
        Course expectedResult = new Course(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), CourseStatusEnum.PROGRAMED, CourseTurnEnum.MORNING, "curso inicial de java", null, null);
        CourseDTO dto = new CourseDTO(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);

        // ACT - Calling a Method/Unit that is being tested
        Course result = courseConverter.dtoToEntity(dto);

        // ASSERT - Verify that the expected result is correct or not
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedResult, result);
    }
    
    @Test
    @DisplayName("It should turn Course entity into a valid CourseDTO")
    void testEntityToDto() {
        // ARRANGE - Setting up the data that required for the test case
        CourseDTO expectedResult = new CourseDTO(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);
        Course entity = new Course(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), CourseStatusEnum.PROGRAMED, CourseTurnEnum.MORNING, "curso inicial de java", null, null);

        // ACT - Calling a Method/Unit that is being tested
        CourseDTO result = courseConverter.entityToDto(entity);

        // ASSERT - Verify that the expected result is correct or not
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedResult, result);
    }
}
