package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.models.Student;
import crud_lms.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final StudentService studentService;

    public CompanyService(CompanyRepository companyRepository, StudentService studentService){
        this.companyRepository = companyRepository;
        this.studentService = studentService;
    }

    public void saveCompany(Company company){
        companyRepository.saveCompany(company);
    }

    public List<Company> findAllCompanies(){
        return companyRepository.findAllCompanies();
    }

    public Company show(long id){
        return companyRepository.show(id);
    }

    public void update (Company company, long companyId){
        companyRepository.updateCompany(company, companyId);
    }

    public void merge(Company company) {
        companyRepository.mergeCompany(company);
    }

    public Company findById(long id){
        return companyRepository.findById(id)
                .orElse(null);
    }

    public void deleteById(long id){
        companyRepository.deleteById(id);
    }

//    public List<Student> findStudentByCompany(Long companyId){
//        List<Student> studentList = studentService.findAllStudents();
////        studentList.stream().filter(student -> student.getGroup().)
//        return studentList;
//
//    }
}
