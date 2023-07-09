package ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.Admin;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.AdminDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("AdminConverter")
@AllArgsConstructor(onConstructor = @__(
        @Autowired))
public class AdminConverter extends Converter<Admin, AdminDTO> {

    private PasswordEncoder encoder;

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
        if (dto.getPassword() != null) {
            dto.setPassword(encoder.encode(dto.getPassword()));
        }
        entity = modelMapper.map(dto, Admin.class);
        if (dto.getId() != null) entity.setId(dto.getId());
        return entity;
    }
}
