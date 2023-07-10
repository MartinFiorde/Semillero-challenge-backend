package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Student;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.StudentDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("StudentConverter")
public class StudentConverter extends Converter<Student, StudentDTO> {
 
    private PasswordEncoder encoder;

    @Autowired
    public StudentConverter(PasswordEncoder encoder, ModelMapper modelMapper) {
        super(modelMapper);
        this.encoder = encoder;
    }
    
    @Override
    public StudentDTO entityToDto(Student entity) {
        StudentDTO dto = modelMapper.map(entity, StudentDTO.class);
        dto.setPassword(null);
        if (entity.getRole() != null) dto.setRole(entity.getRole().name());
        return dto;
    }

    @Override
    public Student dtoToEntity(StudentDTO dto) throws ParseException {
        Student entity = modelMapper.map(dto, Student.class);
        if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
        entity = modelMapper.map(dto, Student.class);
        if (StringUtility.notNullEmpty(dto.getRole())) entity.setRole(UserRole.valueOf(dto.getRole()));
        return entity;
    }
}