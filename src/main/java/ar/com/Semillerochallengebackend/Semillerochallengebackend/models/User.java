package ar.com.Semillerochallengebackend.Semillerochallengebackend.models;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.UserRoleEnum;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false)
    private String id;

    @Column(columnDefinition = "Bit(1) default true")
    private boolean active = true;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registrationDate;

    @Column(length = 32)
    private String email;

    @Column(length = 64)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Column(length = 32)
    private String firstName;

    @Column(length = 32)
    private String lastName;

    @Column(length = 8)
    private String dni;

    @Column(length = 64)
    private String gmailUser;

    @Column(length = 32)
    private String discordUser;

    @ManyToMany
    private List<Course> courses;

}
