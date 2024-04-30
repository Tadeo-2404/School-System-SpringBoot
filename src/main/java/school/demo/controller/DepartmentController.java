package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.service.DepartmentService;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<Object> getDepartments(
            @RequestParam(required = false) String name
    ) {
        if(name != null) {
            return departmentService.getDepartmentsByName(name);
        }
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<Object> getDepartmentById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentsById(departmentId);
    }

    @PostMapping("/departments")
    public ResponseEntity<Object> createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department.getName());
    }

    @PutMapping("/departments")
    public ResponseEntity<Object> editDepartment(@RequestBody Department department) {
        return departmentService.editDepartment(department.getId(), department.getName());
    }

    @DeleteMapping("/departments")
    public ResponseEntity<Object> deleteDepartment(@RequestBody Department department) {
        return departmentService.deleteDepartment(department.getId());
    }
}
