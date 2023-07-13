package ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface CourseServiceInterface extends CRUDServiceInterface<CourseDTO> {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CourseDTO create(CourseDTO d) throws ServiceRuntimeException;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CourseDTO addTeacher(String id) throws ServiceRuntimeException;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CourseDTO deleteTeacher(String id) throws ServiceRuntimeException;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public UserDTO addCourseToUser(String courseId) throws ServiceRuntimeException;
    
    @Transactional(readOnly = true)
    public List<UserDTO> getStudentsByCourse(String courseId);
}