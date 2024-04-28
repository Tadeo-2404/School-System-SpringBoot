package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Student;
import school.demo.repository.StudentRepository;
import school.demo.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<Object> getStudentByID(int studentId) {
        try {
            Map<String, Object> data = new HashMap<>();
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                data.put("data", student);
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Student getStudentByEmail(String student_email) {
        return null;
    }

    public List<Student> getStudents() {
        return null;
    }

    public List<Student> getStudentsByName(String student_name) {
        return null;
    }

    public Student createStudent(int student_id, String student_name, String student_email) {
        return null;
    }

    public Student editStudent(String student_name, String student_email) {
        return null;
    }

    public void deleteStudent(int student_id) {
    }
}
