package ar.com.Semillerochallengebackend.Semillerochallengebackend.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRoleEnum;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter adminConverter = new UserConverter();

    @Test
    @DisplayName("It should turn UserDTO into a valid User Entity with encrypted password and empty list of courses")
    public void testDtoToEntity() {
        System.out.println("dtoToEntity");

        User expectedResult = new User(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", null, UserRoleEnum.ADMIN, "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());
        UserDTO dto = new UserDTO(null, true, LocalDateTime.parse("2023-07-30T00:00:00"), "martinlirio@gmail.com", "12345678", "ADMIN", "Martín", "Fiordelisi", "35511912", "martin.lirio@gmail.com", "Soul6515", new ArrayList<>());

        User result = adminConverter.dtoToEntity(dto);

        assertTrue(new BCryptPasswordEncoder().matches("12345678", result.getPassword()));
        result.setPassword(null);
        assertEquals(expectedResult, result);
        assertEquals(0, result.getCourses() != null ? result.getCourses().size() : -1);
    }
}
