package ar.com.Semillerochallengebackend.Semillerochallengebackend.validators;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseTurn;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CourseValidator {
    
    public CourseDTO validateRegister(CourseDTO dto, String passwordConfirm) {
//        validatePasswords(dto.getPassword(), passwordConfirm);
//        validateEmail(dto.getEmail());
//        validateRole(dto.getRole());
//        validateGenericString(dto.getFirstName());
//        validateGenericString(dto.getLastName());
//        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public CourseDTO validateUpdate(CourseDTO dto) throws ServiceRuntimeException {
//        validateRole(dto.getRole());
//        validateGenericString(dto.getFirstName());
//        validateGenericString(dto.getLastName());
//        validateGenericString(dto.getFirstName());
//        validateGenericString(dto.getDiscordCourse());
//        validateGenericString(dto.getGmailCourse());
//        validateGenericString(dto.getDni());
//        dto.setRegistrationDate(LocalDateTime.now());
        return dto;
    }

    public String validateRole(String role) throws ServiceRuntimeException {
        validateGenericString(role);
        CourseTurn[] courseTurns = CourseTurn.values();
        for (CourseTurn courseTurn : courseTurns) {
            if (courseTurn.toString().equals(role)) {
                return role;
            }
        }
        throw new ServiceRuntimeException("El turno ingresado no es válido.");
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

    public Course validateActiveStatus(Course course) throws ServiceRuntimeException {
        if (!course.isActive()) {
            throw new ServiceRuntimeException("El usuario solicitado se encuentra deshabilitado.");
        }
        return course;
    }
    
}
