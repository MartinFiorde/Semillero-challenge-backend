package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.CourseService;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.ACTIVATE;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.DEACTIVATE;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.DETAIL;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.EDIT;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.GET_ID;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.LIST;
import static ar.com.Semillerochallengebackend.Semillerochallengebackend.utils.Constants.REGISTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String registerCourse(ModelMap model) {
        model.put("dto", new CourseDTO());
        return "course/register.html";
    }

    @PostMapping(REGISTER)
    @PreAuthorize("permitAll()")
    public String registerCoursePost(ModelMap model, @ModelAttribute CourseDTO dto, @RequestParam String passwordConfirm) {
        try {
            courseService.create(dto, passwordConfirm);
            model.put("msg", "Se ha registrado correctamente!");
            return "/index.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "course/register.html";
        }
    }

    @GetMapping(LIST)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String detailOtherCourseData(ModelMap model) {
        model.put("dtos", courseService.getAllActives());
        return "course/list.html";
    }

    @GetMapping(DETAIL + GET_ID)
    @PreAuthorize("isAuthenticated()")
    public String detailOtherCourseData(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("dto", courseService.getOne(id));
        return "course/detail.html";
    }

    @GetMapping(DEACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deactivateCourse(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario desactivado");
        model.put("dto", courseService.deactivate(id));
        return "course/detail.html";
    }

    @GetMapping(ACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String activateCourse(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario reactivado!");
        model.put("dto", courseService.activate(id));
        return "course/detail.html";
    }

    @GetMapping(EDIT + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String changeOtherCourseData(ModelMap model, @PathVariable String id) {
        model.put("dto", courseService.getOne(id));
        return "course/update-data.html";
    }

    @PostMapping(EDIT)
    @PreAuthorize("isAuthenticated()")
    public String changeOtherCourseDataPost(ModelMap model, @ModelAttribute CourseDTO dto) {
        try {
            courseService.edit(dto);
            model.put("msg", "Los datos se han editado correctamente!");
            model.put("dto", courseService.getOne(dto.getId()));
            return "course/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "course/update-data.html";
        }
    }
}
