package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.UserServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validations.UserValidation;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {
    
    // INSTANCES AND CONSTRUCTOR
    private UserRepository userRepository;
    private UserConverter userConverter;
    private UserValidation userValidation;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userValidation = userValidation;
    }
    
    // METHODS
    @Override
    public UserDTO register(String firstName, String lastName, String email, String pass, String pass2) throws ServiceRuntimeException {
        User user = new User();
        user.setRegistrationDate(LocalDateTime.now());
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(userValidation.validatePasswords(pass, pass2)));
        
        return userConverter.entityToDto(user);
    }
    
    @Override
    public UserDTO save(UserDTO d) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO edit(UserDTO d) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO getOne(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> getAllActives() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO deactivate(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> getStudentsByCourse(String courseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> findLikeName(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO addCourse(String courseId) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
