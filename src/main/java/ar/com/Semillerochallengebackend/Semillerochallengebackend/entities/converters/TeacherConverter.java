package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Teacher;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("TeacherConverter")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherConverter extends Converter<Teacher, TeacherDTO> {
 
    private PasswordEncoder encoder;
    
    @Override
    public TeacherDTO entityToDto(Teacher entity) {
        TeacherDTO dto = modelMapper.map(entity, TeacherDTO.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public Teacher dtoToEntity(TeacherDTO dto) throws ParseException {
        Teacher entity = modelMapper.map(dto, Teacher.class);
        if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
        entity = modelMapper.map(dto, Teacher.class);
        if (dto.getId() != null) entity.setId(dto.getId()); 
        return entity;
    }
}