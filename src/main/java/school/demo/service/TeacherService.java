package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Department;

public interface TeacherService {
    ResponseEntity<Object> getTeachers();
    ResponseEntity<Object> getTeachersById(int id);
    ResponseEntity<Object> getTeachersByName(String name);
    ResponseEntity<Object> getTeachersByEmail(String email);
    ResponseEntity<Object> getTeachersByDepartmentId(Department department);
    ResponseEntity<Object> createTeacher(String name, String email, Department department);
    ResponseEntity<Object> editTeacher(int id, String name, String email, Department department);
    ResponseEntity<Object> deleteTeacher(int id);
}
