package ar.com.Semillerochallengebackend.Semillerochallengebackend.validators;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.StringUtils;
import java.time.LocalDateTime;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO validateRegister(UserDTO dto, String passwordConfirm) {
        validatePasswords(dto.getPassword(), passwordConfirm);
        validateEmail(dto.getEmail());
        validateRole(dto.getRole());
        validateGenericString(dto.getFirstName());
        validateGenericString(dto.getLastName());
        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public UserDTO validateUpdate(UserDTO dto) throws ServiceRuntimeException {
        validateRole(dto.getRole());
        validateGenericString(dto.getFirstName());
        validateGenericString(dto.getLastName());
        validateGenericString(dto.getFirstName());
        validateGenericString(dto.getDiscordUser());
        validateGenericString(dto.getGmailUser());
        validateGenericString(dto.getDni());
        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public String validateRole(String role) throws ServiceRuntimeException {
        validateGenericString(role);
        UserRole[] userRoles = UserRole.values();
        for (UserRole userRole : userRoles) {
            if (userRole.toString().equals(role)) {
                return role;
            }
        }
        throw new ServiceRuntimeException("El rol ingresado no es válido.");
    }

    public String validatePasswords(String password, String passwordConfirm) throws ServiceRuntimeException {
        if (StringUtils.nullOrEmpty(password) || password.trim().length() < 4) {
            throw new ServiceRuntimeException("La clave debe tener más de 4 carácteres.");
        }
        if (!password.equals(passwordConfirm)) {
            throw new ServiceRuntimeException("Las claves no coinciden.");
        }
        return password;
    }

    public String validateGenericString(String string) throws ServiceRuntimeException {
        if (StringUtils.nullOrEmpty(string)) {
            throw new ServiceRuntimeException("No puede haber campos vacios.");
        }
        return string;
    }

    public String validateEmail(String email) throws ServiceRuntimeException {
        if (StringUtils.nullOrEmpty(email)) {
            throw new ServiceRuntimeException("Debe ingresar un email.");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new ServiceRuntimeException("Debe ingresar un mail válido.");
        }

        if (userRepository.findByEmail(email).size() >= 1) {
            throw new ServiceRuntimeException("Ya existe un usuario con el mail seleccionado.");
        }
        return email.toLowerCase();
    }

    public User validateActiveStatus(User user) throws ServiceRuntimeException {
        if (!user.isActive()) {
            throw new ServiceRuntimeException("El usuario solicitado se encuentra deshabilitado.");
        }
        return user;
    }
}
