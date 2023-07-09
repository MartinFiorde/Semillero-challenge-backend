package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

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
@AllArgsConstructor
public class Teacher extends User {
    
    @Column(length = 32)
    private String fullName;
    
    @OneToMany
    private List<Course> courses;

}
