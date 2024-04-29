package school.demo.service.Implementation;

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
    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<Object> getStudentByID(int studentId) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                data.put("data", student);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStudentByEmail(String student_email) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Student> optionalStudent = studentRepository.findByEmail(student_email);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                data.put("data", student);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStudents() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Student> list = studentRepository.findAll();
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("message", "not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStudentsByName(String student_name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Student> list = studentRepository.findByName(student_name);
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createStudent(String student_name, String student_email) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (student_email == null || student_name == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            } else {
                Student student = studentRepository.save(new Student(student_email, student_name));
                data.put("data", student);
                data.put("statusMessage", HttpStatus.CREATED);
                data.put("statusCode", HttpStatus.CREATED.value());
                return new ResponseEntity<>(data, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editStudent(int student_id, String student_name, String student_email) {
        Map<String, Object> data = new HashMap<>();
        Integer studentIdInteger = student_id;

        try {
            if(studentIdInteger == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (student_email == null && student_name == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name and Email attributes");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            boolean existStudent = studentRepository.existsById(student_id);
            if(!existStudent) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Students with ID '" + student_id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            Student student = studentRepository.save(new Student(student_id, student_email, student_name));
            data.put("data", student);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Student edited successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteStudent(int student_id) {
        Map<String, Object> data = new HashMap<>();
        Integer studentIdInteger = student_id;

        try {
            if(studentIdInteger == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!studentRepository.existsById(student_id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_ACCEPTABLE.value());
                data.put("message", "Students with ID '" + student_id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            studentRepository.deleteById(student_id);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Student removed successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
