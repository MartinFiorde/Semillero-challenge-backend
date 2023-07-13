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

    public CourseDTO updateValidator(CourseDTO dto) throws ServiceRuntimeException {
        return createValidator(dto);
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

    public String validateGenericString(String string) throws ServiceRuntimeException {
        if (StringUtils.nullOrEmpty(string)) {
            throw new ServiceRuntimeException("No puede haber campos vacios.");
        }
        return string;
    }

    public Integer validateCommission(Integer number) throws ServiceRuntimeException {
        validateGenericString(number.toString());
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
            throw new ServiceRuntimeException("El curso solicitado se encuentra deshabilitado.");
        }
        return course;
    }

}
