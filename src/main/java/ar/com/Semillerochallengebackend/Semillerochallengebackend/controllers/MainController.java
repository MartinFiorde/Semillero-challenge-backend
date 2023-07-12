package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.REGISTER;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.INDEX;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.LOGIN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "redirect:"+INDEX;
    }

    @GetMapping(INDEX)
    @PreAuthorize("permitAll()")
    public String index() {
        return "index.html";
    }

    @GetMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String formularioUsuario(ModelMap model, @RequestParam(required = false) String idSesion) throws ServiceRuntimeException {
        model.put("dto", new UserDTO());
        return "user/register.html";
    }

    @PostMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String cargarUsuario(ModelMap model, @ModelAttribute UserDTO dto, @RequestParam String passwordConfirm) {
        try {
            userService.register(dto, passwordConfirm);
            model.put("msg", "Se ha registrado correctamente!");
            model.put("dto", new UserDTO());
            return "user/register.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "user/register.html";
        }
    }
    
    @GetMapping(LOGIN)
    @PreAuthorize("permitAll()")
    public String registroUsuario(ModelMap model, @RequestParam(required = false) String email, @RequestParam(required = false) String password, @PathVariable(required = false) String msg) {
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("msg", msg);
        return "user/login.html";
    }
}
