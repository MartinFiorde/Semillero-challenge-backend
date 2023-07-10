package ar.com.Semillerochallengebackend.Semillerochallengebackend.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Admin;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters.AdminConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.AdminDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AdminConverterTest {

    @InjectMocks
    private AdminConverter adminConverter = new AdminConverter(new BCryptPasswordEncoder(), new ModelMapper());

    @Test
    public void testDtoToEntity() {
        System.out.println("dtoToEntity");
        
        AdminDTO dto = new AdminDTO("Martín Fiordelisi", null, true, "martinlirio", "martinlirio@gmail.com", "12345678", "Admin");
        
        Admin result = adminConverter.dtoToEntity(dto);

        Admin expectedResult = new Admin("Martín Fiordelisi", null, true, "martinlirio", "martinlirio@gmail.coma", "$2a$10$RlxEjxAYNeHulzOFwn3Oy.BVO.A0J76JzIwCmyA53N863NmAyRRBe", UserRole.ADMIN);
        assertEquals(expectedResult, result);
    }
}