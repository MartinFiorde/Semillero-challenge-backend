package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.CourseDTO;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

@Component("CourseConverter")
public class CourseConverter extends Converter<Course, CourseDTO> {

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
