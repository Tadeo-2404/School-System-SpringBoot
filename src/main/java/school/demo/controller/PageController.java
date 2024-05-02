package school.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.demo.model.Department;
import school.demo.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    private final DepartmentService departmentService;

    public PageController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments/page")
    public String department(Model model) {
        ResponseEntity<Object> responseEntity = departmentService.getDepartments();

        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("noDepartmentsFound", true);
        } else {
            Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
            List<Department> departmentList = (List<Department>) responseBody.get("data");
            model.addAttribute("list", departmentList);
            model.addAttribute("noDepartmentsFound", false);
        }

        model.addAttribute("department", new Department());
        return "department";
    }


    @PostMapping("/departments/save")
    public String saveDepartment(@RequestParam String name) {
        departmentService.createDepartment(name, new ArrayList<>());
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/edit")
    public String editDepartment(@ModelAttribute("department") Department department) {
        departmentService.editDepartment(department.getId(), department.getName(), new ArrayList<>());
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@ModelAttribute("department") Department department) {
        departmentService.deleteDepartment(department.getId());
        // Redirect to the department page
        return "redirect:/departments/page";
    }
}
