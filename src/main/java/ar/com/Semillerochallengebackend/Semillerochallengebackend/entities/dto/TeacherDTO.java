package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
public class TeacherDTO extends UserDTO {

    private String fullName;

    private List<CourseDTO> courses;

}
