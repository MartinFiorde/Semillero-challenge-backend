package ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.CRUDServiceInterface;

public interface CourseServiceInterface extends CRUDServiceInterface<CourseDTO> {

    @Transactional(readOnly = true)
    public List<CourseDTO> findByTitle(String email);
    
    @Transactional(readOnly = true)
    public List<CourseDTO> findByTeacherId(String search);
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CourseDTO addTeacher(String id, String teacherId) throws ServiceRuntimeException;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public CourseDTO deleteTeacher(String id) throws ServiceRuntimeException;
}