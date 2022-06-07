package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.models.Course;
import crud_lms.models.Teacher;
import crud_lms.repositories.CourseRepository;
import crud_lms.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

   private final TeacherRepository teacherRepository;
   private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    public void saveTeacher(Teacher teacher,long courseId){

        Course course = courseRepository.findById(courseId);
            course.setTeacher(teacher);
            teacher.setCourse(course);
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
