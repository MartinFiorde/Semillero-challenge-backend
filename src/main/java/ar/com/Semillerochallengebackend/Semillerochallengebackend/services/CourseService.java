package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.CourseRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.CourseServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.CourseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceInterface {

    // INSTANCES AND CONSTRUCTOR
    private CourseRepository courseRepository;
    private CourseConverter courseConverter;
    private CourseValidator courseValidation;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseConverter courseConverter, CourseValidator courseValidation) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
        this.courseValidation = courseValidation;
    }

    // METHODS
    @Override
    public CourseDTO create(CourseDTO d) throws ServiceRuntimeException {
        courseValidation.createValidator(d);
        return save(d);
    }

    @Override
    public CourseDTO save(CourseDTO d) throws ServiceRuntimeException {
        Course course = courseConverter.dtoToEntity(d);
        courseRepository.save(course);
        return courseConverter.entityToDto(course);
    }

    @Override
    public CourseDTO edit(CourseDTO d) throws ServiceRuntimeException {
        Course courseInDB = courseRepository.findById(d.getId()).get();
        Course courseModified = courseConverter.dtoToEntity(d);
//        courseModified.setPassword(courseInDB.getPassword());
//        courseModified.setRegistrationDate(courseInDB.getRegistrationDate());
//        courseModified.setEmail(courseInDB.getEmail());
//        if (courseModified.getRole()==null) {
//            courseModified.setRole(courseInDB.getRole());
//        }
        courseValidation.validateUpdate(courseConverter.entityToDto(courseModified));
        return courseConverter.entityToDto(courseRepository.save(courseModified));
    }

    @Override
    public CourseDTO getOne(String id) throws ServiceRuntimeException {
        CourseDTO d = courseConverter.entityToDto(courseRepository.findById(id).orElse(null));
        if (d == null) {
            throw new ServiceRuntimeException("Course not found");
        }
        return courseConverter.entityToDto(courseRepository.findById(id).orElse(null));
    }

    @Override
    public List<CourseDTO> getAllActives() {
        return courseConverter.entitiesToDto(courseRepository.getAllActive());
    }

    @Override
    public CourseDTO deactivate(String id) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setActive(false);
        courseRepository.save(course);
        return getOne(id);
    }

    @Override
    public CourseDTO activate(String id) throws ServiceRuntimeException {
        Course course = courseRepository.findById(id).orElse(null);
        course.setActive(true);
        courseRepository.save(course);
        return getOne(id);
    }

    public Course findActiveById(String id) throws ServiceRuntimeException {
        return courseValidation.validateActiveStatus(courseRepository.findById(id).orElse(null));
    }

//
//
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
