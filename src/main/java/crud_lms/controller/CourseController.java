package crud_lms.controller;

import crud_lms.models.Company;
import crud_lms.models.Course;
import crud_lms.services.CompanyService;
import crud_lms.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;


   public CourseController(CourseService courseService, CompanyService companyService){
       this.courseService = courseService;
       this.companyService = companyService;
   }

    @ModelAttribute("courseList")
    public List<Course> findAllCourses(){
        return courseService.findAllCourses();
    }

    @ModelAttribute("companyList")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
    }

    @GetMapping
    public String findAll(){
       return "course/allCourses";
    }

    @GetMapping("/save")
    public String saveCoursePage(Model model){
        model.addAttribute("emptyCourse", new Course());
        return "course/saveCoursePage";
    }

    @PostMapping("/save")
    public String saveCourse(Course course){
        courseService.saveCourse(course, course.getCompanyId());
        return "redirect:/api/courses";
    }

    @GetMapping("/{id}/update")
    public String updateCourse(Model model, @PathVariable("id") long id){
        model.addAttribute("courseUpdate",courseService.show(id));
        return "course/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("courseUpdate") Course course,
                         @PathVariable("id") long id) {
        courseService.update(course,id);
        return "redirect:/api/courses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        courseService.deleteById(id);
        return "redirect:/api/courses";
    }
}
