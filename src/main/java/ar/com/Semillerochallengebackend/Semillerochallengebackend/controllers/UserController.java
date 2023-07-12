package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.USER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(USER)
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changePass")
    @PreAuthorize("isAuthenticated()")
    public String formularioCambioClave(ModelMap model) {
        return USER + "/update-pass.html";
    }

    @PostMapping("/changePass")
    @PreAuthorize("isAuthenticated()")
    public String cargarCambioClave(ModelMap model, @RequestParam String password, @RequestParam String passwordNew, @RequestParam String passwordConfirm) {
        try {
            userService.editPassword(password, passwordNew, passwordConfirm);
            model.put("msg", "La clave se ha cambiado correctamente!");
            return USER + "/update-pass.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            return USER + "/update-pass.html";
        }
    }

}
