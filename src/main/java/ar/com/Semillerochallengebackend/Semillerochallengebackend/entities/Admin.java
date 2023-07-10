package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Admin extends User {
    
    @Column(length = 32)
    private String fullName;

    public Admin(String fullName, String id, boolean active, String userName, String email, String password, UserRole role) {
        super(id, active, userName, email, password, role);
        this.fullName = fullName;
    }


}
