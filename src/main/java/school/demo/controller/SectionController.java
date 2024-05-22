package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.service.SectionService;
import school.demo.utils.CustomResponse;
import school.demo.utils.SectionRequest;
import school.demo.utils.SectionRequestEdit;

/**
 * This class represents a REST controller to manage sections.
 * It implements the functionalities defined in the SectionService interface.
 *
 * @see school.demo.service.SectionService
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@RestController
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/sections")
    public ResponseEntity<CustomResponse> getSections(
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
    public ResponseEntity<CustomResponse> getSectionById(Integer section_id) {
        return sectionService.getSectionById(section_id);
    }

    @PostMapping("/sections")
    public ResponseEntity<CustomResponse> createSection(@RequestBody SectionRequest sectionRequest) {
        String nameSection = sectionRequest.section();
        int department = sectionRequest.departmentId();
        int course = sectionRequest.courseId();
        int teacher = sectionRequest.teacherId();
        return sectionService.createSection(nameSection, department, course, teacher);
    }

    @PutMapping("/sections")
    public ResponseEntity<CustomResponse> editSection(@RequestBody SectionRequestEdit sectionRequestEdit) {
        int id = sectionRequestEdit.sectionId();
        String nameSection = sectionRequestEdit.section();
        int department = sectionRequestEdit.departmentId();
        int course = sectionRequestEdit.courseId();
        int teacher = sectionRequestEdit.teacherId();
        return sectionService.editSection(id ,nameSection, department, course, teacher);
    }

    @DeleteMapping("/sections/{section_id}")
    public ResponseEntity<CustomResponse> deleteSection(@PathVariable Integer section_id) {
        return sectionService.deleteSection(section_id);
    }
}
