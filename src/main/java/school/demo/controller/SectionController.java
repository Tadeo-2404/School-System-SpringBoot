package school.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import school.demo.service.SectionService;
import school.demo.utils.SectionRequest;

@RestController
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections")
    public ResponseEntity<Object> getSections(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) Integer teacherId,
            @RequestParam(required = false) String teacherName,
            @RequestParam(required = false) String teacherEmail
    ) {
        if(name != null) {
            return sectionService.getSectionByName(name);
        } else if(departmentId != null) {
            return sectionService.getSectionById(departmentId);
        } else if(departmentName != null) {
            return sectionService.getSectionsByDepartmentName(departmentName);
        } else if(teacherId != null) {
            return sectionService.getSectionsByTeacherId(teacherId);
        } else if(teacherName != null) {
            return sectionService.getSectionsByTeacherName(teacherName);
        } else if(teacherEmail != null) {
            return sectionService.getSectionsByTeacherEmail(teacherEmail);
        }
        return sectionService.getSections();
    }

    @GetMapping("/sections/{section_id}")
    public ResponseEntity<Object> getSectionById(Integer section_id) {
        return sectionService.getSectionById(section_id);
    }

    @PostMapping("/sections")
    public ResponseEntity<Object> createSection(@RequestBody SectionRequest sectionRequest) {
        Section section = sectionRequest.getSection();
        Department department = sectionRequest.getDepartment();
        Teacher teacher = sectionRequest.getTeacher();
        return sectionService.createSection(section.getName(), department, teacher);
    }

    @PutMapping("/sections")
    public ResponseEntity<Object> editSection(@RequestBody SectionRequest sectionRequest) {
        Section section = sectionRequest.getSection();
        Department department = sectionRequest.getDepartment();
        Teacher teacher = sectionRequest.getTeacher();
        return sectionService.editSection(section.getId(), section.getName(), department, teacher);
    }

    @DeleteMapping("/sections/{section_id}")
    public ResponseEntity<Object> deleteSection(@PathVariable Integer section_id) {
        return sectionService.deleteSection(section_id);
    }
}
