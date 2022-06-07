package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.models.Course;
import crud_lms.models.Group;
import crud_lms.models.Student;
import crud_lms.repositories.CourseRepository;
import crud_lms.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GroupService {


    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    public GroupService(GroupRepository groupRepository, CourseRepository courseRepository) {
        this.groupRepository = groupRepository;
        this.courseRepository = courseRepository;
    }

  public void saveGroup(Group group, long courseId) {
        Course course = courseRepository.findById(courseId);
            group.setCourse(course);
            course.setGroup(group);
            groupRepository.saveGroup(group);

}

    public List<Group> findAllGroups(){
        return groupRepository.findAllGroups();
    }

    public Group show(long id){
        return groupRepository.show(id);
    }

    public void update (Group group, long groupId){
        groupRepository.updateGroup(group, groupId);
    }


    public Group findById(long id){
        return groupRepository.findById(id);
    }

    public void deleteById(long id){
        groupRepository.deleteById(id);
    }

    public List<Student> findStudentsByName(String studentName) {
        return groupRepository.findStudentsByName(studentName);
    }

    public List<Student> findAllStudents() {
        return groupRepository.findAllStudents();
    }

}
