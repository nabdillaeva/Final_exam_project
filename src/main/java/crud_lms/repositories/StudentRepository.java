package crud_lms.repositories;

import crud_lms.models.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public  void saveStudent(Student student){
        entityManager.getTransaction().begin();

        entityManager.persist(student);

        entityManager.getTransaction().commit();
    }

    List<Student> students = new ArrayList<Student>();

    public List<Student> findAllStudents(){
        entityManager.getTransaction().begin();

        students = entityManager.createQuery("select g from Student g ", Student.class).getResultList();

        entityManager.getTransaction().commit();

        return students;

    }

    public Student findById(long studentId){

        entityManager.getTransaction().begin();

       Student student= entityManager.find(Student.class, studentId);

        entityManager.getTransaction().commit();

        return student;
    }

    public Student show(long id) {
        return students.stream().filter(student -> student.getId() == id).findAny().orElse(null);

    }
    public void updateStudent(Student student,long studentId){

        entityManager.getTransaction().begin();

        Student student1 = show(studentId);

        student1.setFirstName(student.getFirstName());

        student1.setLastName(student.getLastName());

        student1.setEmail(student.getEmail());

        student1.setStudyFormat(student.getStudyFormat());


        entityManager.getTransaction().commit();

    }
    public void deleteById(long studentId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Student.class,studentId));

        entityManager.getTransaction().commit();
    }

    public List<Student> findStudentByName(String studentName) {
        entityManager.getTransaction().begin();

        List<Student> resultList = entityManager.createQuery("select s from Student s where s.firstName=?1", Student.class)
                .setParameter(1, studentName).getResultList();

        entityManager.getTransaction().commit();

        return resultList;
    }

    public List<Student> sizeOfStudents(Long companyId){
        entityManager.getTransaction().begin();

        List<Student> resultList = entityManager.createQuery("select st from Student st join Group g on g.id = st.group.id join Course c on c.id = g.course.id join Company  c2 on c2.id=c.company.id where c2.id=?1", Student.class)
                .setParameter(1, companyId)
                .getResultList();

        entityManager.getTransaction().commit();
        return resultList;

    }
}
