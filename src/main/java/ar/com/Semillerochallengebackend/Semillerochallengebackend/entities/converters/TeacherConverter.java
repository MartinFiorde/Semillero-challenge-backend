package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Teacher;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.TeacherDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("TeacherConverter")
public class TeacherConverter extends Converter<Teacher, TeacherDTO> {

    private PasswordEncoder encoder;

    @Autowired
    public TeacherConverter(PasswordEncoder encoder, ModelMapper modelMapper) {
        super(modelMapper);
        this.encoder = encoder;
    }

    @Override
    public TeacherDTO entityToDto(Teacher entity) {
        TeacherDTO dto = modelMapper.map(entity, TeacherDTO.class);
        dto.setPassword(null);
        if (entity.getRole() != null) dto.setRole(entity.getRole().name());
        return dto;
    }

    @Override
    public Teacher dtoToEntity(TeacherDTO dto) throws ParseException {
        Teacher entity = modelMapper.map(dto, Teacher.class);
        if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
        entity = modelMapper.map(dto, Teacher.class);
        if (StringUtility.notNullEmpty(dto.getRole())) entity.setRole(UserRole.valueOf(dto.getRole()));
        return entity;
    }
}
