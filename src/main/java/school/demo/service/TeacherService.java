package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
import school.demo.utils.CustomResponse;

/**
 * This interface represents the definition of the Services for the class Teacher
 * It includes GET, POST, PUT, DELETE
 * The body definition for each method is listed on TeacherServiceImplementation
 * @see school.demo.service.Implementation.TeacherServiceImplementation
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
public interface TeacherService {
    /**
     * Retrieves all teachers.
     * @return ResponseEntity<school.demo.utils.CustomResponse> containing a list of teachers
     */
    ResponseEntity<CustomResponse> getTeachers();
    /**
     * Retrieves a teacher by their ID.
     * @param id the ID of the teacher to retrieve
     * @return ResponseEntity<CustomResponse> containing the teacher if found,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<CustomResponse> getTeachersById(int id);
    /**
     * Retrieves teachers by name.
     * @param name the name of the teacher
     * @return ResponseEntity<CustomResponse> containing a list of teachers with the specified name
     */
    ResponseEntity<CustomResponse> getTeachersByName(String name);
    /**
     * Retrieves a teacher by their email.
     * @param email the email address of the teacher
     * @return ResponseEntity<CustomResponse> containing the teacher if found,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<CustomResponse> getTeachersByEmail(String email);
    /**
     * Retrieves teachers by department.
     * @param department the department to which the teachers belong
     * @return ResponseEntity<CustomResponse> containing a list of teachers in the specified department
     */
    ResponseEntity<CustomResponse> getTeachersByDepartmentId(Department department);
    /**
     * Creates a new teacher.
     * @param name       the name of the teacher to create
     * @param email      the email address of the teacher
     * @param department the department to which the teacher belongs
     * @return ResponseEntity<CustomResponse> containing the created teacher
     */
    ResponseEntity<CustomResponse> createTeacher(String name, String email, Department department);
    /**
     * Edits an existing teacher.
     * @param id         the ID of the teacher to edit
     * @param name       the new name of the teacher
     * @param email      the new email address of the teacher
     * @param department the new department to which the teacher belongs
     * @return ResponseEntity<CustomResponse> containing the updated teacher if successful,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<CustomResponse> editTeacher(int id, String name, String email, Department department);
    /**
     * Deletes a teacher by their ID.
     * @param id the ID of the teacher to delete
     * @return ResponseEntity<CustomResponse> with status 200 if successful,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<CustomResponse> deleteTeacher(int id);
}
