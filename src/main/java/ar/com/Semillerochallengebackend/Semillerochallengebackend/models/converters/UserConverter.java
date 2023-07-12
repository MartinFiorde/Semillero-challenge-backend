package ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("UserConverter")
public class UserConverter extends Converter<User, UserDTO> {
 
    @Autowired
    public UserConverter() {
        super();
    }
    
    @Override
    public UserDTO entityToDto(User entity) {
        UserDTO dto = modelMapper.map(entity, UserDTO.class);
        dto.setPassword(null);
        //if (entity.getRole() != null) dto.setRole(entity.getRole().name());
        return dto;
    }

    @Override
    public User dtoToEntity(UserDTO dto) throws ParseException {
        User entity = modelMapper.map(dto, User.class);
        if (dto.getPassword() != null) dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        entity = modelMapper.map(dto, User.class);
        //if (StringUtils.nullOrEmpty(dto.getRole())) entity.setRole(UserRole.valueOf(dto.getRole()));
        return entity;
    }
}