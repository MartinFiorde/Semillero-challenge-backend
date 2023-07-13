package ar.com.Semillerochallengebackend.Semillerochallengebackend.services;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.User;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.converters.UserConverter;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.repositories.UserRepository;
import java.util.List;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.interfaces.UserServiceInterface;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.validators.UserValidator;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserValidator userValidation;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, UserValidator userValidation) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userValidation = userValidation;
    }

    // METHODS
    @Override
    public UserDTO register(UserDTO d, String passwordConfirm) throws ServiceRuntimeException {
        userValidation.validateRegister(d, passwordConfirm);
        return save(d);
    }

    @Override
    public UserDTO save(UserDTO d) throws ServiceRuntimeException {
        User user = userConverter.dtoToEntity(d);
        userRepository.save(user);
        return userConverter.entityToDto(user);
    }

    @Override
    public UserDTO edit(UserDTO d) throws ServiceRuntimeException {
        User userInDB = userRepository.findById(d.getId()).get();
        User userModified = userConverter.dtoToEntity(d);
        userModified.setPassword(userInDB.getPassword());
        userModified.setRegistrationDate(userInDB.getRegistrationDate());
        userModified.setEmail(userInDB.getEmail());
        if (userModified.getRole()==null) {
            userModified.setRole(userInDB.getRole());
        }
        userValidation.validateUpdate(userConverter.entityToDto(userModified));
        return userConverter.entityToDto(userRepository.save(userModified));
    }

    public void editPassword(String password, String passwordNew, String passwordConfirm) throws ServiceRuntimeException {
        validPasswordSession(password);
        userValidation.validatePasswords(passwordNew, passwordConfirm);
        User userInDB = userRepository.findById(sessionId()).orElse(null);
        userInDB.setPassword(new BCryptPasswordEncoder().encode(passwordNew));
        userRepository.save(userInDB);
    }
    
    @Override
    public UserDTO getOne(String id) throws ServiceRuntimeException {
        UserDTO d = userConverter.entityToDto(userRepository.findById(id).orElse(null));
        if (d==null) throw new ServiceRuntimeException("User not found");
        return userConverter.entityToDto(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserDTO> getAllActives() {
        return userConverter.entitiesToDto(userRepository.getAllActive());
    }

    @Override
    public UserDTO deactivate(String id) {
        User user = userRepository.findById(id).orElse(null);
        user.setActive(false);
        userRepository.save(user);
        return getOne(id);
    }

    @Override
    public UserDTO activate(String id) throws ServiceRuntimeException {
        User user = userRepository.findById(id).orElse(null);
        user.setActive(true);
        userRepository.save(user);
        return getOne(id);
    }
    
    @Override
    public List<UserDTO> getStudentsByCourse(String courseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDTO> findLikeName(String firstName, String lastName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO addCourse(String courseId) throws ServiceRuntimeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).isEmpty() ? null : userRepository.findByEmail(email).get(0);
        if (user == null) throw new UsernameNotFoundException("User not found");
        if (!user.isActive()) throw new UsernameNotFoundException("User not active");
        List<GrantedAuthority> permissions = new ArrayList();
        GrantedAuthority rolePermissions = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
        permissions.add(rolePermissions);

        // Save the user object in the session attributes
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usersesion", user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), permissions);
    }
    
    public String sessionId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String pathmail = "";
        if (principal instanceof UserDetails) {
            pathmail = ((UserDetails) principal).getUsername();
        } else {
            pathmail = principal.toString();
        }
        String idsesion = userRepository.findByEmail(pathmail).get(0).getId();
        return idsesion;
    }
    
    public boolean validPasswordSession(String password) throws ServiceRuntimeException {
        if (!new BCryptPasswordEncoder().matches(password, userRepository.findById(sessionId()).get().getPassword())) {
            throw new ServiceRuntimeException("La clave anterior no es correcta");
        }
        return true;
    }
    
    public User findActiveById(String id) throws ServiceRuntimeException {
        return userValidation.validateActiveStatus(userRepository.findById(id).orElse(null));
    }
}