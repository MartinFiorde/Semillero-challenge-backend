package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseStatus;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseTurn;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // @Setter(value = AccessLevel.NONE)
    @Column(nullable = false)
    private String id;

    @Column(columnDefinition = "Bit(1) default true")
    private boolean active = true;

    @Column(length = 64)
    private String title;

    private Integer commission;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    
    @Enumerated(EnumType.STRING)
    protected CourseStatus status;
    
    @Enumerated(EnumType.STRING)
    private CourseTurn turn;
    
    private String description;
    
    private String teacherId;
    
    private String teacherFullName;

}
