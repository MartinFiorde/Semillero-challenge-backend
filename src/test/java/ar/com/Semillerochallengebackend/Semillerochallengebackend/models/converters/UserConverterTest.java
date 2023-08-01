package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.UserRoleEnum;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    @DisplayName("It should turn UserDTO into a valid User Entity with encrypted password and empty list of courses")
    public void testDtoToEntity() {
        User expectedResult = new User(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", null, UserRoleEnum.ADMIN, "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        UserDTO dto = new UserDTO(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", "12345678", "ADMIN", "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());

        User result = userConverter.dtoToEntity(dto);

        assertTrue(new BCryptPasswordEncoder().matches("12345678", result.getPassword()));
        result.setPassword(null);
        assertEquals(expectedResult, result);
        assertEquals(0, result.getCourses() != null ? result.getCourses().size() : -1);
    }

    @Test
    @DisplayName("It should turn User Entity into a valid UserDTO with and empty list of courses")
    public void testEntityToDto() {
        UserDTO expectedResult = new UserDTO(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", null, "ADMIN", "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        User entity = new User(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", null, UserRoleEnum.ADMIN, "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());

        UserDTO result = userConverter.entityToDto(entity);

        assertEquals(expectedResult, result);
        assertEquals(0, result.getCourses() != null ? result.getCourses().size() : -1);
    }
}
