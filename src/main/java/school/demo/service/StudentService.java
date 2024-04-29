package school.demo.service;

import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<Object> getStudentByID(int student_id);
    ResponseEntity<Object> getStudentByEmail(String student_email);
    ResponseEntity<Object> getStudents();
    ResponseEntity<Object> getStudentsByName(String student_name);
    ResponseEntity<Object> createStudent(String student_name, String student_email);
    ResponseEntity<Object> editStudent(int student_id, String student_name, String student_email);
    ResponseEntity<Object> deleteStudent(int student_id);
}
