package ar.com.Semillerochallengebackend.Semillerochallengebackend.validators;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseValidatorTest {

    @InjectMocks
    private CourseValidator courseValidator;

    @Test
    @DisplayName("It should throw exception when commission input is negative")
    public void testValidateCommission() throws Exception {
        // ARRANGE - Setting up the data that required for the test case
        String expectedResult = "Debe ingresar un numero de comisión válido.";
        Integer param1 = -2;

        // ACT - Calling a Method/Unit that is being tested
        ServiceRuntimeException result = assertThrows(ServiceRuntimeException.class, () -> courseValidator.validateCommission(param1));

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result.getMessage());
        assertNotNull(result);
    }
}
