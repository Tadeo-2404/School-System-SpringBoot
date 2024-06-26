package school.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.demo.model.*;
import school.demo.service.*;
import school.demo.utils.CustomResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents a controller for handling page requests.
 * It provides access to services related to departments, students, teachers, courses, and sections.
 *
 * @see /main/resources/templates
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@Controller
public class PageController {
    private final DepartmentService departmentService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final SectionService sectionService;

    @Autowired
    public PageController(DepartmentService departmentService, StudentService studentService, TeacherService teacherService, CourseService courseService, SectionService sectionService) {
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.sectionService = sectionService;
    }

    //DEPARTMENTS
    @GetMapping("/departments/page")
    public String department(Model model) {
        ResponseEntity<CustomResponse> responseEntity = departmentService.getDepartments();

        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("noDepartmentsFound", true);
        } else {
            List<Department> departmentList = (List<Department>) responseEntity.getBody().getData();
            model.addAttribute("list", departmentList);
            model.addAttribute("noDepartmentsFound", false);
        }

        model.addAttribute("department", new Department());
        return "department";
    }

    @PostMapping("/departments/save")
    public String saveDepartment(@RequestParam String name) {
        ResponseEntity<CustomResponse> response = departmentService.createDepartment(name);
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/edit")
    public String editDepartment(@ModelAttribute("department") Department department) {
        ResponseEntity<CustomResponse> response = departmentService.editDepartment(department.getId(), department.getName());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@ModelAttribute("department") Department department) {
        ResponseEntity<CustomResponse> response = departmentService.deleteDepartment(department.getId());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    //STUDENTS
    @GetMapping("/students/page")
    public String student(Model model) {
        ResponseEntity<CustomResponse> responseEntity = studentService.getStudents();
        ResponseEntity<CustomResponse> responseEntitySections = sectionService.getSections();

        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("studentsFound", false);
        } else {
            List<Student> studentList = (List<Student>) responseEntity.getBody().getData();
            model.addAttribute("studentList", studentList);
            model.addAttribute("studentsFound", true);
        }

        if(responseEntitySections.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("sectionsFound", false);
        } else {
            model.addAttribute("sectionsFound", true);
            //teacher list
            List<Section> sectionList = (List<Section>) responseEntitySections.getBody().getData();
            model.addAttribute("sectionList", sectionList);
        }

        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        List<Section> sectionList = student.getSections() != null ? student.getSections() : null;
        ResponseEntity<CustomResponse> response = studentService.createStudent(student.getName(), student.getEmail(), sectionList);
        String message = response.getBody().getMessage();
        String statusCode = response.getStatusCode().toString();

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the student page
        return "redirect:/students/page";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute("student") Student student) {
        String name = student.getName() != null ? student.getName() : null;
        String email = student.getEmail() != null ? student.getEmail() : null;

        ResponseEntity<CustomResponse> response = studentService.editStudent(student.getId(), name, email, null);
        String message = response.getBody().getMessage();
        String statusCode = response.getStatusCode().toString();

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the student page
        return "redirect:/students/page";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        ResponseEntity<CustomResponse> response = studentService.deleteStudent(student.getId());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/students/page";
    }

    //TEACHERS
    @GetMapping("/teachers/page")
    public String teacher(Model model) {
        ResponseEntity<CustomResponse> responseEntityTeacher = teacherService.getTeachers();
        ResponseEntity<CustomResponse> responseEntityDepartment = departmentService.getDepartments();

        if(responseEntityTeacher.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("teachersFound", false);
        } else {
            model.addAttribute("teachersFound", true);
            //teacher list
            List<Teacher> teacherList = (List<Teacher>) responseEntityTeacher.getBody().getData();
            model.addAttribute("list", teacherList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            List<Department> departmentList = (List<Department>) responseEntityDepartment.getBody().getData();
            model.addAttribute("departmentList", departmentList);
        }

        model.addAttribute("teacher", new Teacher());
        return "teacher";
    }

    @PostMapping("/teachers/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        ResponseEntity<CustomResponse> response = teacherService.createTeacher(teacher.getName(), teacher.getEmail(), teacher.getDepartment());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        return "redirect:/teachers/page";
    }

    @PostMapping("/teachers/edit")
    public String editTeacher(@ModelAttribute("teacher") Teacher teacher) {
        String name = teacher.getName() != null ? teacher.getName() : null;
        String email = teacher.getEmail() != null ? teacher.getEmail() : null;

        Department department = teacher.getDepartment() != null ? teacher.getDepartment() : null;
        ResponseEntity<CustomResponse> response = teacherService.editTeacher(teacher.getId(), name, email, department);

        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the teacher page
        return "redirect:/teachers/page";
    }

    @PostMapping("/teachers/delete")
    public String deleteTeacher(@ModelAttribute("teacher") Teacher teacher) {
        ResponseEntity<CustomResponse> response = teacherService.deleteTeacher(teacher.getId());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the teacher page
        return "redirect:/teachers/page";
    }

    //COURSES
    @GetMapping("/courses/page")
    public String course(Model model) {
        ResponseEntity<CustomResponse> responseEntityCourse = courseService.getCourses();
        ResponseEntity<CustomResponse> responseEntityDepartment = departmentService.getDepartments();

        if(responseEntityCourse.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("coursesFound", false);
        } else {
            model.addAttribute("coursesFound", true);
            //teacher list
            List<Course> courseList = (List<Course>) responseEntityCourse.getBody().getData();
            model.addAttribute("list", courseList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            List<Department> departmentList = (List<Department>) responseEntityDepartment.getBody().getData();
            model.addAttribute("departmentList", departmentList);
        }

        model.addAttribute("course", new Course());
        return "course";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        ResponseEntity<CustomResponse> response = courseService.createCourse(course.getName(), course.getDepartment());

        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    @PostMapping("/courses/edit")
    public String editCourse(@ModelAttribute("courses") Course course) {
        String name = course.getName() != null ? course.getName() : null;

        Department department = course.getDepartment() != null ? course.getDepartment() : null;
        ResponseEntity<CustomResponse> response = courseService.editCourse(course.getId(), name, department);

        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    @PostMapping("/courses/delete")
    public String deleteCourse(@ModelAttribute("courses") Course course) {
        ResponseEntity<CustomResponse> response = courseService.deleteCourse(course.getId());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusMessage());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    //SECTIONS
    @GetMapping("/sections/page")
    public String section(Model model) {
        ResponseEntity<CustomResponse> responseEntitySection = sectionService.getSections();
        ResponseEntity<CustomResponse> responseEntityTeacher = teacherService.getTeachers();
        ResponseEntity<CustomResponse> responseEntityCourse = courseService.getCourses();
        ResponseEntity<CustomResponse> responseEntityDepartment = departmentService.getDepartments();

        if(responseEntitySection.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("sectionsFound", false);
        } else {
            model.addAttribute("sectionsFound", true);
            //teacher list
            List<Section> sectionList = (List<Section>) responseEntitySection.getBody().getData();
            model.addAttribute("sectionList", sectionList);
        }

        if(responseEntityTeacher.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("teachersFound", false);
        } else {
            model.addAttribute("teachersFound", true);
            //teacher list
            List<Teacher> teacherList = (List<Teacher>) responseEntityTeacher.getBody().getData();
            model.addAttribute("teacherList", teacherList);
        }

        if(responseEntityCourse.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("coursesFound", false);
        } else {
            model.addAttribute("coursesFound", true);
            //teacher list
            List<Course> courseList = (List<Course>) responseEntityCourse.getBody().getData();
            model.addAttribute("courseList", courseList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            List<Department> departmentList = (List<Department>) responseEntityDepartment.getBody().getData();
            model.addAttribute("departmentList", departmentList);
        }

        model.addAttribute("section", new Section());
        return "section";
    }

    @PostMapping("/sections/save")
    public String saveSection(@ModelAttribute("section") Section section) {
        String name = section.getName() != null ? section.getName() : null;
        Integer departmentId = section.getDepartment() != null ? section.getDepartment().getId() : null;
        Integer teacherId = section.getTeacher() != null ? section.getTeacher().getId() : null;
        Integer sectionId = section.getCourse() != null ? section.getCourse().getId() : null;

        ResponseEntity<CustomResponse> response = sectionService.createSection(name, departmentId, teacherId, sectionId);
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }

    @PostMapping("/sections/edit")
    public String editSections(@ModelAttribute("sections") Section section) {
        String name = section.getName() != null ? section.getName() : null;
        Integer departmentId = section.getDepartment().getId() != 0 ? section.getDepartment().getId() : 0;
        Integer teacherId = section.getTeacher().getId() != 0 ? section.getTeacher().getId() : 0;
        Integer courseId = section.getCourse().getId() != 0 ? section.getCourse().getId() : 0;

        ResponseEntity<CustomResponse> response = sectionService.editSection(section.getId(), name, departmentId, courseId, teacherId);
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }

    @PostMapping("/sections/delete")
    public String deleteSections(@ModelAttribute("sections") Section section) {
        ResponseEntity<CustomResponse> response = sectionService.deleteSection(section.getId());
        String message = response.getBody().getMessage();
        String statusCode = String.valueOf(response.getBody().getStatusCode());

        if (statusCode.contains("5") || statusCode.contains("4")) {
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }
}
