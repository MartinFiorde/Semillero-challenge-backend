package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Student;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("StudentConverter")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentConverter extends Converter<Student, StudentDTO> {
 
    private PasswordEncoder encoder;
    
    @Override
    public StudentDTO entityToDto(Student entity) {
        StudentDTO dto = modelMapper.map(entity, StudentDTO.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public Student dtoToEntity(StudentDTO dto) throws ParseException {
        Student entity = modelMapper.map(dto, Student.class);
        if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
        entity = modelMapper.map(dto, Student.class);
        if (dto.getId() != null) entity.setId(dto.getId()); 
        return entity;
    }
}