package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Admin;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.AdminDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("AdminConverter")
public class AdminConverter extends Converter<Admin, AdminDTO> {

    private PasswordEncoder encoder;

    @Autowired
    public AdminConverter(PasswordEncoder encoder, ModelMapper modelMapper) {
        super(modelMapper);
        this.encoder = encoder;
    }
    
    @Override
    public AdminDTO entityToDto(Admin entity) {
        AdminDTO dto = modelMapper.map(entity, AdminDTO.class);
        dto.setPassword(null);
        if (entity.getRole() != null) dto.setRole(entity.getRole().name());
        return dto;
    }

    @Override
    public Admin dtoToEntity(AdminDTO dto) throws ParseException {
        Admin entity = modelMapper.map(dto, Admin.class);
        if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
        entity = modelMapper.map(dto, Admin.class);
        if (StringUtility.notNullEmpty(dto.getRole())) entity.setRole(UserRole.valueOf(dto.getRole()));
        return entity;
    }
}
