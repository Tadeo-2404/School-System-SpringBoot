package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Student;
import school.demo.service.StudentService;

@RestController
public class StudentController {
    private StudentService studentService;

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
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student.getName(), student.getEmail());
    }

    @PutMapping("/students")
    public ResponseEntity<Object> editStudent(@RequestBody Student student) {
        return studentService.editStudent(student.getId(), student.getName(), student.getEmail());
    }

    @DeleteMapping("/students")
    public ResponseEntity<Object> deleteStudent(@RequestBody Student student) {
        return studentService.deleteStudent(student.getId());
    }
}
