package crud_lms.repositories;


import crud_lms.models.Student;
import crud_lms.models.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    private EntityManager entityManager;

    public TeacherRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveTeacher(Teacher teacher) {

        entityManager.getTransaction().begin();

        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
    }

    List<Teacher> teachers = new ArrayList<Teacher>();

    public List<Teacher> findAllTeachers() {

        entityManager.getTransaction().begin();

        teachers = entityManager.createQuery("select g from Teacher g ", Teacher.class).getResultList();

        entityManager.getTransaction().commit();

        return teachers;

    }

    public Teacher findById(long teacherId) {

        entityManager.getTransaction().begin();

        Teacher teacher = entityManager.find(Teacher.class, teacherId);

        entityManager.getTransaction().commit();

        return teacher;
    }

    public Teacher show(long id) {
        return teachers.stream().filter(teacher -> teacher.getId() == id).findAny().orElse(null);

    }

    public void updateTeacher(Teacher teacher, long teacherId) {

        entityManager.getTransaction().begin();

        Teacher teacher1 = show(teacherId);

        teacher1.setFirstName(teacher.getFirstName());

        teacher1.setLastName(teacher.getLastName());

        teacher1.setEmail(teacher.getEmail());

        entityManager.getTransaction().commit();

    }

    public void deleteById(long teacherId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Student.class,teacherId));

        entityManager.getTransaction().commit();
    }
}
