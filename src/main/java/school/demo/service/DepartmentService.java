package school.demo.service;
import org.springframework.http.ResponseEntity;
import school.demo.model.Course;
import school.demo.utils.CustomResponse;

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
     * @return ResponseEntity<school.demo.utils.CustomResponse> containing a list of departments
     */
    ResponseEntity<CustomResponse> getDepartments();
    /**
     * Retrieves a department by its ID.
     * @param id the unique identifier of the department
     * @return ResponseEntity<CustomResponse> containing the department if found,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<CustomResponse> getDepartmentsById(int id);
    /**
     * Retrieves departments by name.
     * @param name the name of the department
     * @return ResponseEntity<CustomResponse> containing a list of departments with the specified name
     */
    ResponseEntity<CustomResponse> getDepartmentsByName(String name);
    /**
     * Creates a new department.
     * @param name    the name of the department to create
     * @return ResponseEntity<CustomResponse> containing the created department
     */
    ResponseEntity<CustomResponse> createDepartment(String name);
    /**
     * Edits an existing department.
     * @param id      the unique identifier of the department to edit
     * @param name    the new name of the department
     * @return ResponseEntity<CustomResponse> containing the updated department if successful,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<CustomResponse> editDepartment(int id, String name);
    /**
     * Deletes a department by its ID.
     * @param id the unique identifier of the department to delete
     * @return ResponseEntity<CustomResponse> with status 200 if successful,
     *         or a 404 response if the department does not exist
     */
    ResponseEntity<CustomResponse> deleteDepartment(int id);
}
