package ar.com.Semillerochallengebackend.Semillerochallengebackend.validators;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.UserRoleEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("It should return ADMIN Role as String for empty database")
    public void testValidateRegistrationRole() {
        // ARRANGE - Setting up the data that required for the test case
        String expectedResult = UserRoleEnum.ADMIN.toString();
        // String mock = "mock";

        // ACT - Calling a Method/Unit that is being tested
        String result = userValidator.validateRegistrationRole();

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result);
        assertNotNull(expectedResult);
    }
    
    @Test
    @DisplayName("It should return STUDENT Role as String for empty database")
    public void testValidateRegistrationRole2() {
        // ARRANGE - Setting up the data that required for the test case
        String expectedResult = UserRoleEnum.STUDENT.toString();
        List<User> mock = new ArrayList<>();
        mock.add(new User());
        when(userRepository.findAll()).thenReturn(mock);

        // ACT - Calling a Method/Unit that is being tested
        String result = userValidator.validateRegistrationRole();

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result);
        assertNotNull(expectedResult);
    }
    
    @Test
    @DisplayName("It should throw exception when password do not contain special char")
    public void testvalidateComplexPasswords() {
        // ARRANGE - Setting up the data that required for the test case
        String expectedResult = "La clave debe ser de 8-20 carácteres, sin espacios y contener minusculas, mayusculas, numeros y carácteres especiales.";
        String param1 = "123QWEasd";

        // ACT - Calling a Method/Unit that is being tested
        ServiceRuntimeException result = assertThrows(ServiceRuntimeException.class, () -> userValidator.validateComplexPasswords(param1));

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult, result.getMessage());
        assertNotNull(result);
    }
}
