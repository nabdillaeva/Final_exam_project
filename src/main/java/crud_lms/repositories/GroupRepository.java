package crud_lms.repositories;

import crud_lms.models.Group;
import crud_lms.models.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


@Repository
public class GroupRepository {

    private EntityManager entityManager;

    public GroupRepository(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public  void saveGroup(Group group){
        entityManager.getTransaction().begin();

        entityManager.persist(group);

        entityManager.getTransaction().commit();
    }

    List<Group> groups = new ArrayList<>();

    public List<Group> findAllGroups(){
        entityManager.getTransaction().begin();

        groups = entityManager.createQuery("select g from Group g ", Group.class).getResultList();

        entityManager.getTransaction().commit();

        return groups;
    }

    public Group findById(long groupId){

        entityManager.getTransaction().begin();

        Group group = entityManager.find(Group.class, groupId);

        entityManager.getTransaction().commit();

        return group;
    }

    public Group show(long id) {
        return groups.stream().filter(group -> group.getId() == id).findAny().orElse(null);

    }
    public void updateGroup(Group group,long groupId){

        entityManager.getTransaction().begin();

        Group group1 = show(groupId);

        group1.setGroupName(group.getGroupName());

        group1.setDateOfStart(group.getDateOfStart());

        group1.setDateOfFinish(group.getDateOfFinish());

        entityManager.getTransaction().commit();

    }
    public void deleteById(long groupId){

        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.find(Group.class,groupId));

        entityManager.getTransaction().commit();
    }

    public List<Student> findStudentsByName(String studentName) {
        return entityManager.createQuery(
                "select g from Group g where (select s from Student s where s.firstName=?1) member of g.students"
                ,Student.class).setParameter(1,studentName).getResultList();

    }

    public List<Student> findAllStudents() {
        return entityManager.createQuery(
                "select g from Group g where (select s from Student s) member of g.students"
                ,Student.class).getResultList();
    }
}
