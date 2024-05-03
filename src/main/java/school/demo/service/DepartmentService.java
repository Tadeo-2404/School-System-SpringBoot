package school.demo.service;
import org.springframework.http.ResponseEntity;
import school.demo.model.Course;

import java.util.List;
/**
 * This interface represents the definition of the Services for the class Department
 * It includes GET, POST, PUT, DELETE
 * The body definition for each method is listed on DepartmentServiceImplementation
 * @see school.demo.service.Implementation.DepartmentServiceImplementation
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
public interface DepartmentService {
    /**
     * Retrieves all departments.
     * @return ResponseEntity<Object> containing a list of departments
     */
    ResponseEntity<Object> getDepartments();
    /**
     * Retrieves a department by its ID.
     * @param id the unique identifier of the department
     * @return ResponseEntity<Object> containing the department if found,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<Object> getDepartmentsById(int id);
    /**
     * Retrieves departments by name.
     * @param name the name of the department
     * @return ResponseEntity<Object> containing a list of departments with the specified name
     */
    ResponseEntity<Object> getDepartmentsByName(String name);
    /**
     * Creates a new department.
     * @param name    the name of the department to create
     * @param courses the list of courses associated with the department
     * @return ResponseEntity<Object> containing the created department
     */
    ResponseEntity<Object> createDepartment(String name, List<Course> courses);
    /**
     * Edits an existing department.
     * @param id      the unique identifier of the department to edit
     * @param name    the new name of the department
     * @param courses the new list of courses associated with the department
     * @return ResponseEntity<Object> containing the updated department if successful,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<Object> editDepartment(int id, String name, List<Course> courses);
    /**
     * Deletes a department by its ID.
     * @param id the unique identifier of the department to delete
     * @return ResponseEntity<Object> with status 200 if successful,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<Object> deleteDepartment(int id);
}
