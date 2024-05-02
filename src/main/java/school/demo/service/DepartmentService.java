package school.demo.service;
import org.springframework.http.ResponseEntity;
import school.demo.model.Course;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<Object> getDepartments();
    ResponseEntity<Object> getDepartmentsById(int id);
    ResponseEntity<Object> getDepartmentsByName(String name);
    ResponseEntity<Object> createDepartment(String name, List<Course> courses);
    ResponseEntity<Object> editDepartment(int id, String name, List<Course> courses);
    ResponseEntity<Object> deleteDepartment(int id);
}
