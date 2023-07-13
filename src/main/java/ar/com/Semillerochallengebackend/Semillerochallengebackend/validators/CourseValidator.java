package ar.com.Semillerochallengebackend.Semillerochallengebackend.validators;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseStatusEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.CourseTurnEnum;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.StringUtils;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class CourseValidator {

    public CourseDTO createValidator(CourseDTO dto) {
        validateGenericString(dto.getTitle());
        validateCommission(dto.getCommission());
        validateStartDate(dto.getStartDate());
        validateStatus(dto.getStatus());
        validateTurn(dto.getTurn());
        validateGenericString(dto.getDescription());
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

    public String validateStatus(String status) throws ServiceRuntimeException {
        validateGenericString(status);
        CourseStatusEnum[] courseStatusEnums = CourseStatusEnum.values();
        for (CourseStatusEnum courseStatusEnum : courseStatusEnums) {
            if (courseStatusEnum.toString().equals(status)) {
                return status;
            }
        }
        throw new ServiceRuntimeException("El turno ingresado no es válido.");
    }

    public String validateTurn(String role) throws ServiceRuntimeException {
        validateGenericString(role);
        CourseTurnEnum[] courseTurns = CourseTurnEnum.values();
        for (CourseTurnEnum courseTurn : courseTurns) {
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

    public Integer validateCommission(Integer number) throws ServiceRuntimeException {
        if (StringUtils.nullOrEmpty(number.toString())) {
            throw new ServiceRuntimeException("No puede haber campos vacios.");
        }
        if (number <= 0) {
            throw new ServiceRuntimeException("Debe ingresar un numero de comisión válido.");
        }
        return number;
    }

    public LocalDate validateStartDate(LocalDate date) throws ServiceRuntimeException {
        //TODO
        return date;
    }

    public Course validateActiveStatus(Course course) throws ServiceRuntimeException {
        if (!course.isActive()) {
            throw new ServiceRuntimeException("El usuario solicitado se encuentra deshabilitado.");
        }
        return course;
    }

}
