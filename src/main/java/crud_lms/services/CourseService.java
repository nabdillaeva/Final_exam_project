package crud_lms.services;

import crud_lms.models.Company;
import crud_lms.models.Course;
import crud_lms.repositories.CompanyRepository;
import crud_lms.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    public CourseService(CourseRepository courseRepository, CompanyRepository companyRepository) {
        this.courseRepository = courseRepository;
        this.companyRepository = companyRepository;
    }

    public void saveCourse(Course course, long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalStateException(
                        "company with id = " + companyId + " not found!"
                ));

        company.setCourse(course);
        course.setCompany(company);

    }

    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    public List<Course> findAllCoursesNotTeacher() {
        List<Course> coursesWithoutTeacher = new ArrayList<>();
        List<Course> allCourses = courseRepository.findAllCourses();
        for (Course course: allCourses) {
            if(course.getTeacher() == null){
                coursesWithoutTeacher.add(course);
            }
        }
        return coursesWithoutTeacher;
    }
    public Course show(long id) {
        return courseRepository.show(id);
    }

    public void update(Course course, long courseId) {
        courseRepository.updateCourse(course, courseId);
    }

    public void merge(Course course) {
        courseRepository.mergeCourse(course);
    }

    public Course findById(long id) {
        return courseRepository.findById(id);
    }

    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }


    public List<Course> findByCompanyId(Long companyId) {
       return courseRepository.findByCompanyId(companyId);
    }
}
