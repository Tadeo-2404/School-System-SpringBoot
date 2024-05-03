package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import school.demo.service.TeacherService;
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
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<Object> getTeachers(
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
    public ResponseEntity<Object> getTeacherById(@PathVariable Integer teacher_id) {
        return teacherService.getTeachersById(teacher_id);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.teacher();
        Department department = teacherRequest.department();
        List<Section> sections = teacherRequest.sections();
        return teacherService.createTeacher(teacher.getName(), teacher.getEmail(), department, sections);
    }

    @PutMapping("/teachers")
    public ResponseEntity<Object> editTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.teacher();
        Department department = teacherRequest.department();
        List<Section> sections = teacherRequest.sections();
        return teacherService.editTeacher(teacher.getId(), teacher.getName(), teacher.getEmail(), department, sections);
    }

    @DeleteMapping("/teachers")
    public ResponseEntity<Object> deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher.getId());
    }
}
