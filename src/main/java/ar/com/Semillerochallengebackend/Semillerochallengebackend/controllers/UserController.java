package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.UserDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.UserService;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.LIST;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.DETAIL;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.GET_ID;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.DEACTIVATE;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.ACTIVATE;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.EDIT_PASS;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.EDIT;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.USER;
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
@RequestMapping(USER)
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(LIST)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String detailOtherUserData(ModelMap model) {
        model.put("dtos", userService.getAllActives());
        return "user/list.html";
    }

    @GetMapping(DETAIL + GET_ID)
    @PreAuthorize("isAuthenticated()")
    public String detailOtherUserData(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        if (id.equals("session")) {
            id = userService.sessionId();
        }
        model.put("dto", userService.getOne(id));
        return "user/detail.html";
    }

    @GetMapping(DEACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deactivateUser(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario desactivado");
        model.put("dto", userService.deactivate(id));
        return "user/detail.html";
    }

    @GetMapping(ACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String activateUser(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario reactivado!");
        model.put("dto", userService.activate(id));
        return "user/detail.html";
    }

    @GetMapping(EDIT_PASS)
    @PreAuthorize("isAuthenticated()")
    public String changePassword(ModelMap model) {
        return "/user/update-pass.html";
    }

    @PostMapping(EDIT_PASS)
    @PreAuthorize("isAuthenticated()")
    public String changePasswordPost(ModelMap model, @RequestParam String password, @RequestParam String passwordNew, @RequestParam String passwordConfirm) {
        try {
            userService.editPassword(password, passwordNew, passwordConfirm);
            model.put("msg", "La clave se ha cambiado correctamente!");
            return "/user/update-pass.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            return "/user/update-pass.html";
        }
    }

    @GetMapping(EDIT + GET_ID)
    @PreAuthorize("isAuthenticated()")
    public String changeOtherUserData(ModelMap model, @PathVariable String id) {
         if (id.equals("session")) {
            id = userService.sessionId();
        }
        model.put("dto", userService.getOne(id));
        return "user/update-data.html";
    }

    @PostMapping(EDIT)
    @PreAuthorize("isAuthenticated()")
    public String changeOtherUserDataPost(ModelMap model, @ModelAttribute UserDTO dto, @RequestParam String id) {
        try {
            userService.edit(dto);
            model.put("msg", "Los datos se han editado correctamente!");
            model.put("dto", userService.getOne(id));
            return "user/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "user/update-data.html";
        }
    }
}
