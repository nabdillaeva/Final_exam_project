package crud_lms.services;

import crud_lms.models.Group;
import crud_lms.models.Student;
import crud_lms.repositories.GroupRepository;
import crud_lms.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }


    public void saveStudent(Student student,long groupId){

        Group group1 = groupRepository.findById(groupId);

//        Group group = groupRepository.findById(groupId).orElseThrow(()->new IllegalStateException(
//                "group with id  = " + groupId + "not found!"
//        ));

        group1.setStudent(student);
        student.setGroup(group1);

    }

    public List<Student> findAllStudents(){
        return studentRepository.findAllStudents();
    }

    public Student show(long id){
        return studentRepository.show(id);
    }

    public void update (Student student, long studentId){
        studentRepository.updateStudent(student, studentId);
    }


    public Student findById(long id){
        return studentRepository.findById(id);
    }

    public void deleteById(long id){
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentsByName(String studentName) {
        return studentRepository.findStudentByName(studentName);
    }
    public List<Student> sizeOfStudents(Long companyId){
        return studentRepository.sizeOfStudents(companyId);
    }
}
