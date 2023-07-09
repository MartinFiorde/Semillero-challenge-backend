package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    
    protected String id;

    protected boolean active = true;

    protected String userName;

    protected String email;

    protected String password;

    protected String role;
    
}
