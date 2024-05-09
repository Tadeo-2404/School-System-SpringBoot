package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Section;
import school.demo.model.Student;
import school.demo.service.StudentService;
import school.demo.utils.StudentRequest;
import java.util.List;

/**
 * This class represents a REST controller to manage students.
 * It implements the functionalities defined in the StudentService interface.
 *
 * @see school.demo.service.StudentService
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@RestController
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<Object> getStudents(@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        if(name != null) {
            return studentService.getStudentsByName(name);
        } else if (email != null) {
            return studentService.getStudentByEmail(email);
        }
        return studentService.getStudents();
    }

    @GetMapping("/students/{student_id}")
    public ResponseEntity<Object> getStudentById(@PathVariable int student_id) {
        return studentService.getStudentByID(student_id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentRequest.student();
        List<Section> sections = studentRequest.sections();
        return studentService.createStudent(student.getName(), student.getEmail(), sections);
    }

    @PutMapping("/students")
    public ResponseEntity<Object> editStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentRequest.student();
        List<Section> sections = student.getSections();
        return studentService.editStudent(student.getId(), student.getName(), student.getEmail(), sections);
    }

    @DeleteMapping("/students")
    public ResponseEntity<Object> deleteStudent(@RequestBody Student student) {
        return studentService.deleteStudent(student.getId());
    }
}
