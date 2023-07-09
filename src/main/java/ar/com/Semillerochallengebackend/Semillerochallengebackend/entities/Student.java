package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

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

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
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

}
