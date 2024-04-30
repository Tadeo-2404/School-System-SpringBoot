package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Department;

public interface CourseService {
    ResponseEntity<Object> getCourses();
    ResponseEntity<Object> getCourseById(int id);
    ResponseEntity<Object> getCourseByName(String name);
    ResponseEntity<Object> getCoursesByDepartmentId(Department department);
    ResponseEntity<Object> getCoursesByDepartmentName(Department department);
    ResponseEntity<Object> createCourse(String name, Department department);
    ResponseEntity<Object> editCourse(int id, String name, Department department);
    ResponseEntity<Object> deleteCourse(int id);
}
