package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
//@JsonInclude(Include.NON_NULL)
public class AdminDTO extends UserDTO {

    private String fullName;

    public AdminDTO(String fullName, String id, boolean active, String userName, String email, String password, String role) {
        super(id, active, userName, email, password, role);
        this.fullName = fullName;
    }

}
