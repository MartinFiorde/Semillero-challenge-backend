package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

// TUTORIAL EXPLICANDO ALLCONSTRUCTOR CLARO Y OPTIMO EN CASOS DE HERENCIA: 
// https://medium.com/@sergio-sanchez/inheritance-with-lombok-annotations-in-java-springboot-f32a2d873fff
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Student extends User {

    @Column(length = 32)
    private String firstNname;

    @Column(length = 32)
    private String lastName;

    @Column(length = 8)
    private String dni;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @Column(length = 64)
    private String gmailUser;

    @Column(length = 32)
    private String discordUser;

    @OneToMany
    private List<Course> courses;

    public Student(String firstNname, String lastName, String dni, LocalDate startDate, String gmailUser, String discordUser, List<Course> courses, String id, boolean active, String userName, String email, String password, UserRole role) {
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
