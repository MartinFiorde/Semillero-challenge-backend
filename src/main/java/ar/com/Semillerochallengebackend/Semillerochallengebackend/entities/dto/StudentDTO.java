package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
public class StudentDTO extends UserDTO {

    private String firstNname;

    private String lastName;

    private String dni;

    private LocalDate startDate;

    private String gmailUser;

    private String discordUser;

    private List<CourseDTO> courses;

    public StudentDTO(String firstNname, String lastName, String dni, LocalDate startDate, String gmailUser, String discordUser, List<CourseDTO> courses, String id, boolean active, String userName, String email, String password, String role) {
        super(id, active, userName, email, password, role);
        this.firstNname = firstNname;
        this.lastName = lastName;
        this.dni = dni;
        this.startDate = startDate;
        this.gmailUser = gmailUser;
        this.discordUser = discordUser;
        this.courses = courses;
    }

}
