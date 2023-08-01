package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

@Component("CourseConverter")
public class CourseConverter extends Converter<Course, CourseDTO> {

    @Autowired
    public CourseConverter() {
        super();
    }
     
    @Override
    public CourseDTO entityToDto(Course entity) {
        CourseDTO dto = modelMapper.map(entity, CourseDTO.class);
        return dto;
    }

    @Override
    public Course dtoToEntity(CourseDTO dto) throws ParseException {
        Course entity = modelMapper.map(dto, Course.class);
        return entity;
    }
    
}
