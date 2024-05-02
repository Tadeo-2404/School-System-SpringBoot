package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.service.DepartmentService;
import school.demo.utils.DepartmentRequest;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest.department().getName(), departmentRequest.courses());
    }

    @PutMapping("/departments")
    public ResponseEntity<Object> editDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.editDepartment(departmentRequest.department().getId() ,departmentRequest.department().getName(), departmentRequest.courses());
    }

    @DeleteMapping("/departments")
    public ResponseEntity<Object> deleteDepartment(@RequestBody Department department) {
        return departmentService.deleteDepartment(department.getId());
    }
}
