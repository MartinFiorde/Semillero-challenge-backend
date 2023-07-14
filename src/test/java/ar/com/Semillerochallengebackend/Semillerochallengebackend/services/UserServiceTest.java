package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.UserRoleEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.UserValidator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    // INSTANCES AND CONSTRUCTOR
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserService(userRepository, new UserConverter(), new UserValidator(userRepository));

    // TESTS
    @Test
    @DisplayName("It should return UserDTO as it should be in the DB")
    public void testValidateRegistrationRole() {
        // ARRANGE - Setting up the data that required for the test case
        UserDTO expectedResult = new UserDTO("1", true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", null, "ADMIN", "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        UserDTO mock = new UserDTO(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", "12345678", "ADMIN", "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        User whenResult = new User("1", true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", "encryptedPassword", UserRoleEnum.ADMIN, "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        when(userRepository.save(any(User.class))).thenReturn(whenResult);

        // ACT - Calling a Method/Unit that is being tested
        UserDTO result = userService.save(mock);

        // ASSERT - Verify that the expected result is correct or not
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getFirstName(), result.getFirstName());
        assertNotNull(result);
    }

    @Test
    @DisplayName("It should return true when password match with an instance of their encrypted version")
    public void testvalidPasswordSession() {
        boolean expectedResult = true;
        String mock = "lolo";
        Optional<User> whenResult = Optional.of(new User("1", true, null, null, "$2a$10$de5v66RRDpkHYC773Nch5OIvH0B5UIl2iP2SHgktdXF1b7JK7kLT2", UserRoleEnum.ADMIN, null, null, null, null, null, null));
        UserService spyUserService = Mockito.spy(userService);
        Mockito.doReturn("1").when(spyUserService).sessionId(); 
        when(userRepository.findById(any(String.class))).thenReturn(whenResult);

        boolean result = spyUserService.validPasswordSession(mock);

        assertEquals(expectedResult, result);
    }
}
