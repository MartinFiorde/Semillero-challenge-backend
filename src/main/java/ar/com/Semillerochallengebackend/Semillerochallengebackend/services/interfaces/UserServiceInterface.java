package ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;

public interface UserServiceInterface extends CRUDServiceInterface<UserDTO>{
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public UserDTO register(UserDTO d, String passwordConfirm) throws ServiceRuntimeException;
    
    @Transactional(readOnly = true)
    public List<UserDTO> findLikeName(String firstName, String lastName);
}