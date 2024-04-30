package school.demo.service;
import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
import school.demo.model.Teacher;

public interface SectionService {
    ResponseEntity<Object> getSections();
    ResponseEntity<Object> getSectionById(Integer id);
    ResponseEntity<Object> getSectionByName(String name);
    ResponseEntity<Object> getSectionsByDepartmentId(Integer departmentId);
    ResponseEntity<Object> getSectionsByDepartmentName(String name);
    ResponseEntity<Object> getSectionsByTeacherId(Integer teacherId);
    ResponseEntity<Object> getSectionsByTeacherName(String name);
    ResponseEntity<Object> getSectionsByTeacherEmail(String email);
    ResponseEntity<Object> createSection(String name, Department department, Teacher teacher);
    ResponseEntity<Object> editSection(Integer id, String name, Department department, Teacher teacher);
    ResponseEntity<Object> deleteSection(Integer id);
}
