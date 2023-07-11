package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.CourseRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.CourseServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validations.CourseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceInterface {

    // INSTANCES AND CONSTRUCTOR
    private CourseRepository courseRepository;
    private CourseConverter courseConverter;
    private CourseValidation courseValidation;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseConverter courseConverter, CourseValidation courseValidation) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
        this.courseValidation = courseValidation;
    }

    // METHODS
    @Override
    public CourseDTO save(CourseDTO d) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseDTO edit(CourseDTO d) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseDTO getOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CourseDTO> getAllActives() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseDTO deactivate(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CourseDTO> findByTitle(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CourseDTO> findByTeacherId(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseDTO addTeacher(String id, String teacherId) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseDTO deleteTeacher(String id) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
