package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.service.CourseService;
import school.demo.utils.CourseRequest;

/**
 * This class represents a REST controller to manage courses.
 * It implements the functionalities defined in the CourseService interface.
 *
 * @see school.demo.service.CourseService
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@RestController
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Object> getCourses(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) String departmentName
    ) {
        if (id != null) {
            return courseService.getCourseById(id);
        } else if(name != null) {
            return courseService.getCourseByName(name);
        } else if (departmentId != null) {
            return courseService.getCoursesByDepartmentId(new Department(departmentId));
        } else if (departmentName != null) {
            return courseService.getCoursesByDepartmentName(new Department(departmentName));
        }
        return courseService.getCourses();
    }

    @GetMapping("/courses/{course_id}")
    public ResponseEntity<Object> getCourseById(@PathVariable Integer course_id) {
        return courseService.getCourseById(course_id);
    }

    @PostMapping("/courses")
    public ResponseEntity<Object> createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseRequest.course();
        Department department = courseRequest.department();
        return courseService.createCourse(course.getName(), department);
    }

    @PutMapping("/courses")
    public ResponseEntity<Object> editCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseRequest.course();
        Department department = courseRequest.department();
        return courseService.editCourse(course.getId(), course.getName(), department);
    }

    @DeleteMapping("/courses")
    public ResponseEntity<Object> deleteCourse(@RequestBody Course course) {
        return courseService.deleteCourse(course.getId());
    }
}
