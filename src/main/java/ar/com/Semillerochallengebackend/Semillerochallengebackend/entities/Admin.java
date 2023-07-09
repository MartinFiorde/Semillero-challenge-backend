package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

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
@AllArgsConstructor
public class Admin extends User {
    
    @Column(length = 32)
    private String fullName;

}
