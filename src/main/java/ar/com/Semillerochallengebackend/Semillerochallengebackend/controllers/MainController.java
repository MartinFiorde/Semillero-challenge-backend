package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.constants.Constants.*;
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
//@PreAuthorize("isAuthenticated()")
//@PreAuthorize("hasAnyRole('ADMIN','ADMIN')")
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("permitAll()")
    public String empty() {
        return "redirect:" + INDEX;
    }

    @GetMapping(INDEX)
    @PreAuthorize("permitAll()")
    public String index() {
        return "index.html";
    }

    @GetMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String registerUser(ModelMap model) {
        model.put("dto", new UserDTO());
        return "user/register.html";
    }

    @PostMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String registerUserPost(ModelMap model, @ModelAttribute UserDTO dto, @RequestParam String passwordConfirm) {
        try {
            model.put("msg", userService.isFirstUser() ? "Se ha registrado como primer usuario. Cuenta con acceso de administrador. Puede editar roles de acceso de otros usuarios" : "Felicidades! Su cuenta de alumno se ha registrado correctamente");
            userService.register(dto, passwordConfirm);
            return "/index.html";
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
    public String userLogin(ModelMap model, @PathVariable(required = false) String msg) {
        model.addAttribute("msg", msg);
        return "user/login.html";
    }
}
