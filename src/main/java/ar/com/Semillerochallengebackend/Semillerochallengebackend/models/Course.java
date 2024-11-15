package ar.com.Semillerochallengebackend.Semillerochallengebackend.models;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseStatusEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.CourseTurnEnum;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    protected CourseStatusEnum status;
    
    @Enumerated(EnumType.STRING)
    private CourseTurnEnum turn;
    
    private String description;
    
    private String teacherId;
    
    private String teacherFullName;

}
