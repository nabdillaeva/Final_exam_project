package crud_lms.services;

import crud_lms.models.Student;
import crud_lms.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void saveStudent(Student student){
        studentRepository.saveStudent(student);
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

}
