package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.CourseConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.Course;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.CourseRepository;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.CourseServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.CourseValidator;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements CourseServiceInterface {

    // INSTANCES AND CONSTRUCTOR
    private CourseRepository courseRepository;
    private CourseConverter courseConverter;
    private CourseValidator courseValidation;
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseConverter courseConverter, CourseValidator courseValidation, UserService userService, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
        this.courseValidation = courseValidation;
        this.userService = userService;
        this.userRepository = userRepository;
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
        courseModified.setActive(courseInDB.isActive());
        courseModified.setTeacherId(courseInDB.getTeacherId());
        courseModified.setTeacherFullName(courseInDB.getTeacherFullName());
        courseValidation.updateValidator(courseConverter.entityToDto(courseModified));
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
    public CourseDTO addTeacher(String id) throws ServiceRuntimeException {
        Course course = courseRepository.findById(id).orElse(null);
        User teacher = userRepository.findById(userService.sessionId()).orElse(null);
        if (teacher == null || course == null) {
            throw new ServiceRuntimeException("El ID ingresado no es válido.");
        }

        course.setTeacherId(teacher.getId());
        course.setTeacherFullName(teacher.getLastName() + " " + teacher.getFirstName());
        courseRepository.save(course);

        List<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses == null) {
            teacherCourses = new ArrayList<>();
        }
        teacherCourses.add(course);
        teacher.setCourses(teacherCourses);
        userRepository.save(teacher);
        return courseConverter.entityToDto(course);
    }

    @Override
    public CourseDTO deleteTeacher(String id) throws ServiceRuntimeException {
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            throw new ServiceRuntimeException("El ID ingresado no es válido.");
        }
        User teacher = userRepository.findById(course.getTeacherId()).orElse(null);
        if (teacher == null) {
            throw new ServiceRuntimeException("El ID ingresado no es válido.");
        }

        course.setTeacherId(null);
        course.setTeacherFullName(null);
        courseRepository.save(course);

        List<Course> teacherCourses = teacher.getCourses();
        if (teacherCourses == null) {
            teacherCourses = new ArrayList<>();
        }
        for (int i = 0; i < teacherCourses.size(); i++) {
            if (teacherCourses.get(i).getId().equals(course.getId())) {
                teacherCourses.remove(i);
                break;
            }
        }
        if (teacherCourses.size() == 0) {
            teacher.setCourses(null);
        } else {
            teacher.setCourses(teacherCourses);
        }
        userRepository.save(teacher);
        return courseConverter.entityToDto(course);
    }

    @Override
    public UserDTO addCourseToUser(String courseId) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> getStudentsByCourse(String courseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
