package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utilities.Constants.REGISTER;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@PreAuthorize("permitAll()")
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String empty() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    @PreAuthorize("permitAll()")
    public String index() {
        return "index.html";
    }

    @GetMapping("/register")
    @PreAuthorize("permitAll()")
    public String formularioUsuario(ModelMap model, @RequestParam(required = false) String idSesion) throws ServiceRuntimeException {
        return "form-register.html";
    }

    @PostMapping(REGISTER)
    @PreAuthorize("permitAll()")
    //agrego el archivo al parametro
    public String cargarUsuario(ModelMap model, String firstName, String lastName, String email, @RequestParam String pass, @RequestParam String pass2) {
        try {
            userService.register(firstName, lastName, email, pass, pass2);
            model.put("msg", "Se ha registrado correctamente!");
            return "form-register.html";
        } catch (Exception ex) {
            System.out.println(ex);
            model.put("msg", ex.getMessage());
            model.put("email", email);
            model.put("pass", pass);
            model.put("pass2", pass2);
            return "form-register.html";
        }
    }

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public String registroUsuario(ModelMap model, @RequestParam(required = false) String mail, @RequestParam(required = false) String clave, @PathVariable(required = false) String error) {
        model.addAttribute("mail", mail);
        model.addAttribute("clave", clave);
        return "login.html";
    }
}
