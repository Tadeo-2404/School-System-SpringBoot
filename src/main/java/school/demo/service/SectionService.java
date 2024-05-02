package school.demo.service;
import org.springframework.http.ResponseEntity;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.model.Student;
import school.demo.model.Teacher;

import java.util.List;

public interface SectionService {
    ResponseEntity<Object> getSections();
    ResponseEntity<Object> getSectionById(Integer id);
    ResponseEntity<Object> getSectionByName(String name);
    ResponseEntity<Object> getSectionsByDepartmentId(Integer departmentId);
    ResponseEntity<Object> getSectionsByDepartmentName(String name);
    ResponseEntity<Object> getSectionsByTeacherId(Integer teacherId);
    ResponseEntity<Object> getSectionsByTeacherName(String name);
    ResponseEntity<Object> getSectionsByTeacherEmail(String email);
    ResponseEntity<Object> createSection(String name, Integer departmentId, Integer courseId, Integer teacherId);
    ResponseEntity<Object> editSection(Integer id, String name, Integer departmentId, Integer courseId, Integer teacherId);
    ResponseEntity<Object> deleteSection(Integer id);
}
