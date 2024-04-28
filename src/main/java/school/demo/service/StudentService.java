package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    ResponseEntity<Object> getStudentByID(int student_id);
    Student getStudentByEmail(String student_email);
    List<Student> getStudents();
    List<Student> getStudentsByName(String student_name);
    Student createStudent(int student_id, String student_name, String student_email);
    Student editStudent(String student_name, String student_email);
    void deleteStudent(int student_id);
}
