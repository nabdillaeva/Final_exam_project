package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
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
}
