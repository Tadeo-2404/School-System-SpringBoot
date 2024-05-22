package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.service.DepartmentService;
import school.demo.utils.CustomResponse;
import school.demo.utils.DepartmentRequest;

/**
 * This class represents a REST controller to manage departments.
 * It implements the functionalities defined in the DepartmentService interface.
 *
 * @see school.demo.service.DepartmentService
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<CustomResponse> getDepartments(
            @RequestParam(required = false) String name
    ) {
        if(name != null) {
            return departmentService.getDepartmentsByName(name);
        }
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    public ResponseEntity<CustomResponse> getDepartmentById(@PathVariable Integer departmentId) {
        return departmentService.getDepartmentsById(departmentId);
    }

    @PostMapping("/departments")
    public ResponseEntity<CustomResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.createDepartment(departmentRequest.department().getName());
    }

    @PutMapping("/departments")
    public ResponseEntity<CustomResponse> editDepartment(@RequestBody DepartmentRequest departmentRequest) {
        return departmentService.editDepartment(departmentRequest.department().getId() ,departmentRequest.department().getName());
    }

    @DeleteMapping("/departments")
    public ResponseEntity<CustomResponse> deleteDepartment(@RequestBody Department department) {
        return departmentService.deleteDepartment(department.getId());
    }
}
