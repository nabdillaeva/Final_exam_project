package crud_lms.controller;

import crud_lms.models.Company;
import crud_lms.models.Student;
import crud_lms.services.CompanyService;
import crud_lms.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final StudentService studentService;

    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }

    @ModelAttribute("companyList")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
    }

    @ModelAttribute("studentList")
    public List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping
    public String allCompanies(){
        return "company/allCompanies";
    }

    @GetMapping("/save")
    public String saveCompanyPage(Model model){
        model.addAttribute("emptyCompany", new Company());
        return "company/saveCompanyPage";
    }

    @PostMapping("/save")
    public String saveCompany(Company company){
        companyService.saveCompany(company);
        return "redirect:/api/companies";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(Model model, @PathVariable("id") long id){
        model.addAttribute("companyUpdate",companyService.show(id));
        return "company/update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("companyUpdate") Company company,
                         @PathVariable("id") long id) {
        companyService.update(company,id);
        return "redirect:/api/companies";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        companyService.deleteById(id);
        return "redirect:/api/companies";
    }

}
