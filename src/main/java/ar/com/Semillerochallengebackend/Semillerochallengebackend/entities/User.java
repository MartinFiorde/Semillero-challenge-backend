package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    protected String id;

    @Column(columnDefinition = "Bit(1) default true")
    protected boolean active = true;

    @Column(length = 32)
    protected String userName;

    @Column(length = 32)
    protected String email;

    @Column(length = 64)
    protected String password;

    @Enumerated(EnumType.STRING)
    protected UserRole role;

}
