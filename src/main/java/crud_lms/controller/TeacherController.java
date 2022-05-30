package crud_lms.controller;

import crud_lms.models.Teacher;
import crud_lms.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ModelAttribute("teacherList")
    public List<Teacher> findAllTeachers() {
        return teacherService.findAllTeachers();
    }

    @GetMapping
    public String findAll() {
        return "teacher/allTeachers";
    }

    @GetMapping("/save")
    public String saveTeacherPage(Model model) {
        model.addAttribute("emptyTeacher", new Teacher());
        return "teacher/saveTeacherPage";
    }

    @PostMapping("/save")
    public String saveTeacher(Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/api/teachers";
    }


    @GetMapping("/{id}/update")
    public String updateTeacher(Model model, @PathVariable("id") long id) {
        model.addAttribute("teacherUpdate", teacherService.show(id));
        return "teacher/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teacherUpdate") Teacher teacher,
                         @PathVariable("id") long id) {
        teacherService.update(teacher, id);
        return "redirect:/api/teachers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        teacherService.deleteById(id);
        return "redirect:/api/teachers";
    }
}
