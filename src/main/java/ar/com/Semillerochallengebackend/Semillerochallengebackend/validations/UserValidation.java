package ar.com.Semillerochallengebackend.Semillerochallengebackend.validations;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    private UserRepository userRepository;

    @Autowired
    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO validateRegister(UserDTO dto, String pass2) {
        validatePasswords(dto.getPassword(), pass2);
        validateEmail(dto.getEmail());
        validateRole(dto.getRole());
        validateGenericString(dto.getFirstName());
        validateGenericString(dto.getLastName());
        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public String validateRole(String role) {
        validateGenericString(role);
        UserRole roles[] = UserRole.values();
        for (UserRole aux : roles) {
            if (aux.toString().equals(role)) {
                return role;
            }
        }
        throw new ServiceRuntimeException("El rol ingresado no es válido.");
    }

    public String validatePasswords(String pass, String pass2) throws ServiceRuntimeException {
        if (StringUtility.nullOrEmpty(pass) || pass.trim().length() < 4) {
            throw new ServiceRuntimeException("La clave debe tener 4 o más carácteres.");
        }
        if (!pass.equals(pass2)) {
            throw new ServiceRuntimeException("Las claves no coinciden.");
        }
        return pass;
    }

    public String validateGenericString(String string) throws ServiceRuntimeException {
        if (StringUtility.nullOrEmpty(string)) {
            throw new ServiceRuntimeException("No puede haber campos vacios");
        }
        return string;
    }

    public String validateEmail(String email) throws ServiceRuntimeException {
        if (StringUtility.nullOrEmpty(email)) {
            throw new ServiceRuntimeException("Debe ingresar un email");
        }

        int dotCount = 0;
        int atSymbolCount = 0;
        for (int i = 0; i < email.length(); i++) {
            if (atSymbolCount > 1) {
                throw new ServiceRuntimeException("Debe ingresar un mail válido.");
            }
            if (email.substring(i, i + 1).equals("@")) {
                atSymbolCount++;
                for (int j = i; j < email.length(); j++) {
                    if (email.substring(j, j + 1).equals(".")) {
                        dotCount++;
                    }
                }
            }
        }
        if (atSymbolCount == 0 || dotCount == 0) {
            throw new ServiceRuntimeException("Debe ingresar un mail válido..");
        }

        for (int i = 0; i < email.length(); i++) {
            if (email.substring(i).equals("@")) {
                dotCount++;
            }
        }
        if (atSymbolCount > 1) {
            throw new ServiceRuntimeException("Debe ingresar un mail válido...");
        }

        if (userRepository.findByEmail(email).size() >= 1) {
            throw new ServiceRuntimeException("Ya existe un usuario con el mail seleccionado.");
        }
        return email.toLowerCase();
    }
}