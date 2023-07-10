package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Teacher extends User {
    
    @Column(length = 32)
    private String fullName;
    
    @OneToMany
    private List<Course> courses;

    public Teacher(String fullName, List<Course> courses, String id, boolean active, String userName, String email, String password, UserRole role) {
        super(id, active, userName, email, password, role);
        this.fullName = fullName;
        this.courses = courses;
    }

}
