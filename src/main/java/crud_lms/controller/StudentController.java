package crud_lms.controller;

import crud_lms.models.Group;
import crud_lms.models.Student;
import crud_lms.services.GroupService;
import crud_lms.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @ModelAttribute("studentList")
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.findAllGroups();
    }

    @GetMapping
    public String findAll(){
        return "student/allStudents";
    }

    @GetMapping("/save")
    public String saveStudentPage(Model model){
        model.addAttribute("emptyStudent", new Student());
        return "student/saveStudentPage";
    }

    @PostMapping("/save")
    public String saveStudent(Student student){
        studentService.saveStudent(student,student.getGroupId());
        return "redirect:/api/students";
    }

    @GetMapping("/{id}/update")
    public String updateStudent(Model model, @PathVariable("id") long id){
        model.addAttribute("studentUpdate",studentService.show(id));
        return "student/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("studentUpdate") Student student,
                         @PathVariable("id") long id) {
        studentService.update(student,id);
        return "redirect:/api/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        studentService.deleteById(id);
        return "redirect:/api/students";
    }

}
