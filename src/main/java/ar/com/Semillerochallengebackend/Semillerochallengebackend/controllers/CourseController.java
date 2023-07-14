package ar.com.Semillerochallengebackend.Semillerochallengebackend.controllers;

import ar.com.Semillerochallengebackend.Semillerochallengebackend.errors.ServiceRuntimeException;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.models.dto.CourseDTO;
import ar.com.Semillerochallengebackend.Semillerochallengebackend.services.CourseService;
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

@Controller
@RequestMapping(COURSE)
@PreAuthorize("isAuthenticated()")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(SAVE)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String createCourse(ModelMap model) {
        model.put("dto", new CourseDTO());
        return "/course/create.html";
    }

    @PostMapping(SAVE)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String createCoursePost(ModelMap model, @ModelAttribute CourseDTO dto) {
        try {
            CourseDTO savedDTO = courseService.create(dto);
            model.put("msg", "El curso se ha creado exitosamente!");
            model.put("dto", savedDTO);
            return "course/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "course/create.html";
        }
    }

    @GetMapping(LIST)
    @PreAuthorize("isAuthenticated()")
    public String detailOtherCourseData(ModelMap model) {
        model.put("dtos", courseService.getAllActives());
        return "course/list.html";
    }

    @GetMapping(DETAIL + GET_ID)
    @PreAuthorize("isAuthenticated()")
    public String detailOtherCourseData(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("dto", courseService.getOne(id));
        model.put("students", courseService.getStudentsByCourse(id));
        return "course/detail.html";
    }

    @GetMapping(DEACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String deactivateCourse(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario desactivado");
        model.put("dto", courseService.deactivate(id));
        model.put("students", courseService.getStudentsByCourse(id));
        return "course/detail.html";
    }

    @GetMapping(ACTIVATE + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String activateCourse(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Usuario reactivado!");
        model.put("dto", courseService.activate(id));
        model.put("students", courseService.getStudentsByCourse(id));
        return "course/detail.html";
    }

    @GetMapping(EDIT + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String changeOtherCourseData(ModelMap model, @PathVariable String id) {
        model.put("dto", courseService.getOne(id));
        return "course/update-data.html";
    }

    @PostMapping(EDIT)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String changeOtherCourseDataPost(ModelMap model, @ModelAttribute CourseDTO dto) {
        try {
            courseService.edit(dto);
            model.put("msg", "Los datos se han editado correctamente!");
            model.put("dto", courseService.getOne(dto.getId()));
            model.put("students", courseService.getStudentsByCourse(dto.getId()));
            return "course/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", dto);
            return "course/update-data.html";
        }
    }

    @GetMapping("/addTeacher" + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String addTeacher(ModelMap model, @PathVariable String id) {
        try {
            model.put("msg", "Te has asignado como profesor del curso.");
            model.put("dto", courseService.addTeacher(id));
            model.put("students", courseService.getStudentsByCourse(id));
            return "course/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", courseService.getOne(id));
            model.put("students", courseService.getStudentsByCourse(id));
            return "course/detail.html";
        }

    }

    @GetMapping("/deleteTeacher" + GET_ID)
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    public String deleteTeacher(ModelMap model, @PathVariable String id) throws ServiceRuntimeException {
        model.put("msg", "Se ha removido al profesor del curso.");
        model.put("dto", courseService.deleteTeacher(id));
        model.put("students", courseService.getStudentsByCourse(id));
        return "course/detail.html";
    }

    @GetMapping("/addToStudent" + GET_ID)
    @PreAuthorize("hasAnyRole('STUDENT')")
    public String addToStudent(ModelMap model, @PathVariable String id) {
        try {
            model.put("msg", "Te has inscripto al curso!");
            model.put("dto", courseService.addToStudent(id));
            model.put("students", courseService.getStudentsByCourse(id));
            return "course/detail.html";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            model.put("msg", ex.getMessage());
            model.put("dto", courseService.getOne(id));
            model.put("students", courseService.getStudentsByCourse(id));
            return "course/detail.html";
        }
    }

}
