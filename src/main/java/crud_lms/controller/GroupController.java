package crud_lms.controller;


import crud_lms.models.Course;
import crud_lms.models.Group;
import crud_lms.models.Student;
import crud_lms.services.CourseService;
import crud_lms.services.GroupService;
import crud_lms.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;

    public GroupController(GroupService groupService, CourseService courseService, StudentService studentService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @ModelAttribute("groupList")
    public List<Group> findAllGroups(){
        return groupService.findAllGroups();
    }
    @ModelAttribute("courseList")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping
    public String findAll(){
        return "group/allGroups";
    }

    @GetMapping("/save")
    public String saveGroupPage(Model model){
        model.addAttribute("emptyGroup", new Group());
        return "group/saveGroupPage";
    }

    @PostMapping("/save")
    public String saveCompany(Group group){
        groupService.saveGroup(group, group.getCourseId());
        return "redirect:/api/groups";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id){
        model.addAttribute("groupUpdate",groupService.show(id));
        return "group/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("groupUpdate") Group group,
                         @PathVariable("id") long id) {
        groupService.update(group,id);
        return "redirect:/api/groups";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        groupService.deleteById(id);
        return "redirect:/api/groups";
    }

    @GetMapping("/search")
    public String home(Model model, String keyword) {
        if (keyword != null) {
            List<Student> list = studentService.findStudentsByName(keyword);
            model.addAttribute("list", list);
        } else {
            List<Student> list = studentService.findAllStudents();
            model.addAttribute("list", list);
        }
        return "student/studentsSearch";

    }

//    @RequestMapping(value = "students",method = RequestMethod.GET)
//    public String showStudentByName( Model model,@RequestParam (value = "name", required = false)String name) {
//        System.out.println("workkk!");
//        model.addAttribute("student", studentService.listStudentsByName(name));
//        System.out.println(name);
//        return "group/searchStudent";
//    }
//@GetMapping("/studentSearch")
//public String showStudentByName(Model model) {
//
//    model.addAttribute("student", new Student());
//
//    return "group/searchStudent";
//}

//   @GetMapping("/studentSearch")
//    public String studentSearch(Model model,@RequestParam(name = "studentName",required=false) String studentName) {
//
//        model.addAttribute("student",studentService.listStudentsByName(studentName));
//
//        System.out.println(studentName);
//        return "group/searchStudent";
//    }
}
