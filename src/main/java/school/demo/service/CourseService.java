package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
/**
 * This interface represents the definition of the Services for the class Course
 * It includes GET, POST, PUT, DELETE
 * The body definition for each method is listed on CourseServiceImplementation.class
 * @see school.demo.service.Implementation.CourseServiceImplementation
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
public interface CourseService {
    /** Returns all available courses */
    ResponseEntity<Object> getCourses();
    /**
     * Retrieves a course based on the provided ID.
     * @param id the ID of the course to retrieve
     * @return ResponseEntity containing 404 if not found or respective data
     */
    ResponseEntity<Object> getCourseById(int id);
    /**
     * Retrieves a course based on the provided Name.
     * @param name the name of the course to retrieve
     * @return ResponseEntity containing 404 if not found or respective data
     */
    ResponseEntity<Object> getCourseByName(String name);
    /**
     * Retrieves a course based on the provided FK Department.id.
     * @param department the attribute containing the id to retrieve
     * @return ResponseEntity<Object> containing 404 if not found or respective data
     */
    ResponseEntity<Object> getCoursesByDepartmentId(Department department);
    /**
     * Retrieves a course based on the provided FK Department.name.
     * @param department the attribute containing the name to retrieve
     * @return ResponseEntity containing 404 if not found or respective data
     */
    ResponseEntity<Object> getCoursesByDepartmentName(Department department);
    /**
     * Creates an object of type Course
     * @param name the attribute containing the given name
     * @param department the department to which the course belongs
     * @return ResponseEntity containing 404 if not found or respective data
     */
    ResponseEntity<Object> createCourse(String name, Department department);
    /**
     * Edit an object of type Course
     * @param id         the unique identifier of the course to retrieve
     * @param name       the name of the course to retrieve
     * @param department the department to which the course belongs
     * @return ResponseEntity containing the course if found,
     */
    ResponseEntity<Object> editCourse(int id, String name, Department department);
    /**
     * Delete an object of type Course
     * @param id         the unique identifier of the course to delete
     * @return ResponseEntity containing the method response,
     */
    ResponseEntity<Object> deleteCourse(int id);
}
