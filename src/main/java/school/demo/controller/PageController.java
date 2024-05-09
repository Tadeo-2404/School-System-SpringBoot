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
        ResponseEntity<Object> response = departmentService.createDepartment(name, new ArrayList<>());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/edit")
    public String editDepartment(@ModelAttribute("department") Department department) {
        ResponseEntity<Object> response = departmentService.editDepartment(department.getId(), department.getName(), new ArrayList<>());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@ModelAttribute("department") Department department) {
        ResponseEntity<Object> response = departmentService.deleteDepartment(department.getId());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/departments/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/departments/page";
    }

    //STUDENTS
    @GetMapping("/students/page")
    public String student(Model model) {
        ResponseEntity<Object> responseEntity = studentService.getStudents();
        ResponseEntity<Object> responseEntitySections = sectionService.getSections();

        if(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("studentsFound", false);
        } else {
            Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
            List<Department> studentList = (List<Department>) responseBody.get("data");
            model.addAttribute("studentList", studentList);
            model.addAttribute("studentsFound", true);
        }

        if(responseEntitySections.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("sectionsFound", false);
        } else {
            model.addAttribute("sectionsFound", true);
            //teacher list
            Map<String, Object> responseBodySection = (Map<String, Object>) responseEntitySections.getBody();
            List<Section> sectionList = (List<Section>) responseBodySection.get("data");
            model.addAttribute("sectionList", sectionList);
        }

        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        List<Section> sectionList = student.getSections() != null ? student.getSections() : null;
        ResponseEntity<Object> response = studentService.createStudent(student.getName(), student.getEmail(), sectionList);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the student page
        return "redirect:/students/page";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute("student") Student student) {
        String name = student.getName() != null ? student.getName() : null;
        String email = student.getEmail() != null ? student.getEmail() : null;
        ResponseEntity<Object> response = studentService.editStudent(student.getId(), name, email, null);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the student page
        return "redirect:/students/page";
    }

    @PostMapping("/students/delete")
    public String deleteStudent(@ModelAttribute("student") Student student) {
        ResponseEntity<Object> response = studentService.deleteStudent(student.getId());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/students/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the department page
        return "redirect:/students/page";
    }

    //TEACHERS
    @GetMapping("/teachers/page")
    public String teacher(Model model) {
        ResponseEntity<Object> responseEntityTeacher = teacherService.getTeachers();
        ResponseEntity<Object> responseEntityDepartment = departmentService.getDepartments();
        System.out.println("responseEntityDepartment: " + responseEntityDepartment);

        if(responseEntityTeacher.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("teachersFound", false);
        } else {
            model.addAttribute("teachersFound", true);
            //teacher list
            Map<String, Object> responseBodyTeacher = (Map<String, Object>) responseEntityTeacher.getBody();
            List<Teacher> teacherList = (List<Teacher>) responseBodyTeacher.get("data");
            model.addAttribute("list", teacherList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            Map<String, Object> responseBodyDepartment = (Map<String, Object>) responseEntityDepartment.getBody();
            List<Department> departmentList = (List<Department>) responseBodyDepartment.get("data");
            model.addAttribute("departmentList", departmentList);
        }

        model.addAttribute("teacher", new Teacher());
        return "teacher";
    }

    @PostMapping("/teachers/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        ResponseEntity<Object> response = teacherService.createTeacher(teacher.getName(), teacher.getEmail(), teacher.getDepartment(), null);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        return "redirect:/teachers/page";
    }

    @PostMapping("/teachers/edit")
    public String editTeacher(@ModelAttribute("teacher") Teacher teacher) {
        String name = teacher.getName() != null ? teacher.getName() : null;
        String email = teacher.getEmail() != null ? teacher.getEmail() : null;
        Department department = teacher.getDepartment() != null ? teacher.getDepartment() : null;
        ResponseEntity<Object> response = teacherService.editTeacher(teacher.getId(), name, email, department,null);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the teacher page
        return "redirect:/teachers/page";
    }

    @PostMapping("/teachers/delete")
    public String deleteTeacher(@ModelAttribute("teacher") Teacher teacher) {
        ResponseEntity<Object> response = teacherService.deleteTeacher(teacher.getId());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/teachers/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the teacher page
        return "redirect:/teachers/page";
    }

    //COURSES
    @GetMapping("/courses/page")
    public String course(Model model) {
        ResponseEntity<Object> responseEntityCourse = courseService.getCourses();
        ResponseEntity<Object> responseEntityDepartment = departmentService.getDepartments();

        if(responseEntityCourse.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("coursesFound", false);
        } else {
            model.addAttribute("coursesFound", true);
            //teacher list
            Map<String, Object> responseBodyCourse = (Map<String, Object>) responseEntityCourse.getBody();
            List<Course> courseList = (List<Course>) responseBodyCourse.get("data");
            model.addAttribute("list", courseList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            Map<String, Object> responseBodyDepartment = (Map<String, Object>) responseEntityDepartment.getBody();
            List<Department> departmentList = (List<Department>) responseBodyDepartment.get("data");
            model.addAttribute("departmentList", departmentList);
        }

        model.addAttribute("course", new Course());
        return "course";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        ResponseEntity<Object> response = courseService.createCourse(course.getName(), course.getDepartment());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    @PostMapping("/courses/edit")
    public String editCourse(@ModelAttribute("courses") Course course) {
        String name = course.getName() != null ? course.getName() : null;
        Department department = course.getDepartment() != null ? course.getDepartment() : null;
        ResponseEntity<Object> response = courseService.editCourse(course.getId(), name, department);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    @PostMapping("/courses/delete")
    public String deleteCourse(@ModelAttribute("courses") Course course) {
        ResponseEntity<Object> response = courseService.deleteCourse(course.getId());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/courses/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        // Redirect to the course page
        return "redirect:/courses/page";
    }

    //SECTIONS
    @GetMapping("/sections/page")
    public String section(Model model) {
        ResponseEntity<Object> responseEntitySection = sectionService.getSections();
        ResponseEntity<Object> responseEntityTeacher = teacherService.getTeachers();
        ResponseEntity<Object> responseEntityCourse = courseService.getCourses();
        ResponseEntity<Object> responseEntityDepartment = departmentService.getDepartments();

        if(responseEntitySection.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("sectionsFound", false);
        } else {
            model.addAttribute("sectionsFound", true);
            //teacher list
            Map<String, Object> responseBodySection = (Map<String, Object>) responseEntitySection.getBody();
            List<Section> sectionList = (List<Section>) responseBodySection.get("data");
            model.addAttribute("sectionList", sectionList);
        }

        if(responseEntityTeacher.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("teachersFound", false);
        } else {
            model.addAttribute("teachersFound", true);
            //teacher list
            Map<String, Object> responseBodyTeacher = (Map<String, Object>) responseEntityTeacher.getBody();
            List<Teacher> teacherList = (List<Teacher>) responseBodyTeacher.get("data");
            model.addAttribute("teacherList", teacherList);
        }

        if(responseEntityCourse.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("coursesFound", false);
        } else {
            model.addAttribute("coursesFound", true);
            //teacher list
            Map<String, Object> responseBodyCourse = (Map<String, Object>) responseEntityCourse.getBody();
            List<Course> courseList = (List<Course>) responseBodyCourse.get("data");
            model.addAttribute("courseList", courseList);
        }

        if(responseEntityDepartment.getStatusCode() == HttpStatus.NOT_FOUND) {
            model.addAttribute("departmentsFound", false);
        } else {
            model.addAttribute("departmentsFound", true);
            //department list
            Map<String, Object> responseBodyDepartment = (Map<String, Object>) responseEntityDepartment.getBody();
            List<Department> departmentList = (List<Department>) responseBodyDepartment.get("data");
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

        ResponseEntity<Object> response = sectionService.createSection(name, departmentId, teacherId, sectionId);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }

    @PostMapping("/sections/edit")
    public String editSections(@ModelAttribute("sections") Section section) {
        String name = section.getName() != null ? section.getName() : null;
        Integer departmentId = section.getDepartment().getId() != 0 ? section.getDepartment().getId() : 0;
        Integer teacherId = section.getTeacher().getId() != 0 ? section.getTeacher().getId() : 0;
        Integer courseId = section.getCourse().getId() != 0 ? section.getCourse().getId() : 0;
        ResponseEntity<Object> response = sectionService.editSection(section.getId(), name, departmentId, courseId, teacherId);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }

    @PostMapping("/sections/delete")
    public String deleteSections(@ModelAttribute("sections") Section section) {
        ResponseEntity<Object> response = sectionService.deleteSection(section.getId());
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        Object message = responseBody.get("message");
        Object statusCode = responseBody.get("statusCode");

        if (statusCode.toString().contains("5") || statusCode.toString().contains("4")) {
            String errorMessage = message.toString();
            // Append the error message as a query parameter in the redirect URL
            return "redirect:/sections/page?errorMessage=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
        }
        return "redirect:/sections/page";
    }
}
