package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.entities.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.enums.UserRole;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.UserServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validations.UserValidation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public UserDTO register(UserDTO dto, String pass2) throws ServiceRuntimeException {
        User user = userConverter.dtoToEntity(userValidation.validateRegister(dto, pass2));
        userRepository.save(user);
        System.out.println(user.getId());
        System.out.println(user.getPassword());
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
        User user = userRepository.findByEmail(email).isEmpty() ? null : userRepository.findByEmail(email).get(0);
        try {
            if (user == null) throw new ServiceRuntimeException("Usuario no encontrado");
        } catch (Exception ex) {
            System.out.println(ex);
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        List<GrantedAuthority> permissions = new ArrayList();
        GrantedAuthority rolePermissions = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
        permissions.add(rolePermissions);

        // guardado del objeto usuario en la sesion
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usersesion", user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), permissions);
    }
}
