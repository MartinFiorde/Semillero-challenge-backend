package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseStatusEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseTurnEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.CourseRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.CourseValidator;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.UserValidator;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    // INSTANCES AND CONSTRUCTOR
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CourseService courseService = new CourseService(courseRepository, new CourseConverter(), new CourseValidator(), new UserService(userRepository, new UserConverter(), new UserValidator(userRepository)), userRepository, new UserConverter());
    
    // TESTS
    @Test
    @DisplayName("It should return CourseDTO as it should be in the DB")
    public void testValidateRegistrationRole() {
        // ARRANGE - Setting up the data that required for the test case
        CourseDTO expectedResult = new CourseDTO("1", true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);
        CourseDTO mock = new CourseDTO(null, true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), "PROGRAMED", "MORNING", "curso inicial de java", null, null);
        Course whenResult = new Course("1", true, "Java Inicial", 1, LocalDate.parse("2023-07-30"), CourseStatusEnum.PROGRAMED, CourseTurnEnum.MORNING, "curso inicial de java", null, null);
        when(courseRepository.save(any(Course.class))).thenReturn(whenResult);
        
        // ACT - Calling a Method/Unit that is being tested
        CourseDTO result = courseService.save(mock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getTitle(), result.getTitle());
        assertNotNull(result);
    }
}
