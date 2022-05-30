package crud_lms.services;

import crud_lms.models.Teacher;
import crud_lms.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

   private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void saveTeacher(Teacher teacher){
        teacherRepository.saveTeacher(teacher);
    }

    public List<Teacher> findAllTeachers(){
        return teacherRepository.findAllTeachers();
    }
    public Teacher show(long id){
        return teacherRepository.show(id);
    }

    public void update (Teacher teacher, long teacherId){
        teacherRepository.updateTeacher(teacher,teacherId);
    }
    public Teacher findById(long id){
        return teacherRepository.findById(id);
    }
    public void deleteById(long id){
        teacherRepository.deleteById(id);
    }

}
