package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.model.Teacher;
import school.demo.service.TeacherService;
import school.demo.utils.TeacherRequest;

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
        Teacher teacher = teacherRequest.getTeacher();
        Department department = teacherRequest.getDepartment();
        return teacherService.createTeacher(teacher.getName(), teacher.getEmail(), department);
    }

    @PutMapping("/teachers")
    public ResponseEntity<Object> editTeacher(@RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = teacherRequest.getTeacher();
        Department department = teacherRequest.getDepartment();
        return teacherService.editTeacher(teacher.getId(), teacher.getName(), teacher.getEmail(), department);
    }

    @DeleteMapping("/teachers")
    public ResponseEntity<Object> deleteTeacher(@RequestBody Teacher teacher) {
        return teacherService.deleteTeacher(teacher.getId());
    }
}
