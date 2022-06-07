package crud_lms.repositories;

import crud_lms.models.Company;
import crud_lms.models.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class CompanyRepository {

    private EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveCompany(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }

    List<Company> companies = new ArrayList<>();

    public List<Company> findAllCompanies() {

        entityManager.getTransaction().begin();

        companies = entityManager.createQuery("select c from Company c", Company.class)
                .getResultList();

        entityManager.getTransaction().commit();

        return companies;

    }

    public Company show(long id) {
        return companies.stream().filter(company -> company.getId() == id).findAny().orElse(null);

    }

    public void updateCompany(Company company, long companyId) {

        entityManager.getTransaction().begin();

        Company company1 = show(companyId);

        company1.setCompanyName(company.getCompanyName());

        company1.setLocatedCountry(company.getLocatedCountry());

        entityManager.getTransaction().commit();

    }

    public void mergeCompany(Company company) {

        entityManager.getTransaction().begin();

        entityManager.merge(company);

        entityManager.getTransaction().commit();
    }

    public Optional<Company> findById(long id) {

        entityManager.getTransaction().begin();

        Optional<Company> company = Optional.ofNullable(entityManager.find(Company.class, id));

        entityManager.getTransaction().commit();

        return company;
    }

    public void deleteById(long companyId) {

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Company.class, companyId));

        entityManager.getTransaction().commit();
    }

//    public List<Student> findStudentByCompany(Long companyId){
//
//        entityManager.getTransaction().begin();
//
//        List<Student> students = entityManager.createQuery("select st from Student st join Group g on st.group_id = g.id join g.courses c on g.id=c.groups_id join Course cs on cs.id = cs.groups gs.courses_id join Company c2 on c2.id=c.company_id where c2.id= :id",Student.class)
//                        .setParameter("id",companyId)
//                                .getResultList();
//
//
//        entityManager.getTransaction().commit();
//
//        return null;
//
//    }

//    select s
//    from students s
//    join groups g on s.group_id = g.id
//    join groups_courses gc on g.id = gc.groups_id
//    join courses c on c.id = gc.courses_id
//    join companies c2 on c2.id = c.company_id where c2.id = 5;
}
