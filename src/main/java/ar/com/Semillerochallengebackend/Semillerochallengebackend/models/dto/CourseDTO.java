package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public final class CourseDTO {
    
    private String id;

    private boolean active = true;

    private String title;

    private Integer commission;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    
    protected String status;
    
    private String turn;
    
    private String description;
    
    private String teacherId;
    
    private String teacherFullName;
    
}
