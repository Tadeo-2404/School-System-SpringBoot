package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import school.demo.service.TeacherService;
import school.demo.utils.CustomResponse;
import school.demo.utils.TeacherRequest;
import java.util.List;

/**
 * This class represents a REST controller to manage teachers.
 * It implements the functionalities defined in the TeacherService interface.
 *
 * @see school.demo.service.TeacherService
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<CustomResponse> getTeachers(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer departmentId
    ) {
        if (id != null) {
            return teacherService.getTeachersById(id);
        } else if(name != null) {
            return teacherService.getTeachersByName(name);
        } else if (email != null) {
            return teacherService.getTeachersByEmail(email);
        } else if (departmentId != null) {
            return teacherService.getTeachersByDepartmentId(new Department(departmentId));
        }
        return teacherService.getTeachers();
    }

    @GetMapping("/teachers/{teacher_id}")
    public ResponseEntity<CustomResponse> getTeacherById(@PathVariable Integer teacher_id) {
        return teacherService.getTeachersById(teacher_id);
    }

    @PostMapping("/teachers")
    public ResponseEntity<CustomResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.teacher();
        Department department = teacherRequest.department();
        return teacherService.createTeacher(teacher.getName(), teacher.getEmail(), department);
    }

    @PutMapping("/teachers")
    public ResponseEntity<CustomResponse> editTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.teacher();
        Department department = teacherRequest.department();
        return teacherService.editTeacher(teacher.getId(), teacher.getName(), teacher.getEmail(), department);
    }

    @DeleteMapping("/teachers")
    public ResponseEntity<CustomResponse> deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher.getId());
    }
}
