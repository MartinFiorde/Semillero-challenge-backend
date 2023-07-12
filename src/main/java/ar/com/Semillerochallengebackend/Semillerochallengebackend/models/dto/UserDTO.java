package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO {
    
    private String id;

    private boolean active = true;
    
    private LocalDateTime registrationDate;

    private String email;

    private String password;

    private String role;
    
    private String firstName;

    private String lastName;

    private String dni;

    private String gmailUser;

    private String discordUser;

    private List<CourseDTO> courses;
    
}
