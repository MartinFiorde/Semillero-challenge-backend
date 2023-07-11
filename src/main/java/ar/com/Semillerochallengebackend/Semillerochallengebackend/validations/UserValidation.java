package ar.com.Semillerochallengebackend.Semillerochallengebackend.validations;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.StringUtility;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
    
    public String validatePasswords(String pass, String pass2) throws ServiceRuntimeException {
        if (!StringUtility.notNullEmpty(pass) || pass.trim().length() < 4) {
            throw new ServiceRuntimeException("La clave debe tener 4 o más carácteres.");
        }
        if (!pass.equals(pass2)) {
            throw new ServiceRuntimeException("Las claves no coinciden.");
        }
        return pass;
    }
}
