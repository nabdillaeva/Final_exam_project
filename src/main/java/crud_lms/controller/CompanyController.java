package crud_lms.controller;

import crud_lms.models.Company;
import crud_lms.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ModelAttribute("companyList")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
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

//    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
//    public String delete(@PathVariable int id){
//        companyService.deleteById(id);
//        return "redirect:/  api/companies";
//    }

//    @RequestMapping(value="/edit/{id}")
//    public String edit(@PathVariable int id, Model m){
//        Company company1=companyService.findById(id);
//        m.addAttribute("companies",company1);
//        return "company/update";
//    }
//
//    @RequestMapping(value="/editsave",method = RequestMethod.POST)
//    public String editsave(@ModelAttribute("companyler") Company company){
//        companyService.merge(company);
//        return "redirect:/api/companies";
//    }
}
