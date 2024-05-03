package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
import school.demo.model.Section;

import java.util.List;
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
     * @return ResponseEntity<Object> containing a list of teachers
     */
    ResponseEntity<Object> getTeachers();
    /**
     * Retrieves a teacher by their ID.
     * @param id the ID of the teacher to retrieve
     * @return ResponseEntity<Object> containing the teacher if found,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<Object> getTeachersById(int id);
    /**
     * Retrieves teachers by name.
     * @param name the name of the teacher
     * @return ResponseEntity<Object> containing a list of teachers with the specified name
     */
    ResponseEntity<Object> getTeachersByName(String name);
    /**
     * Retrieves a teacher by their email.
     * @param email the email address of the teacher
     * @return ResponseEntity<Object> containing the teacher if found,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<Object> getTeachersByEmail(String email);
    /**
     * Retrieves teachers by department.
     * @param department the department to which the teachers belong
     * @return ResponseEntity<Object> containing a list of teachers in the specified department
     */
    ResponseEntity<Object> getTeachersByDepartmentId(Department department);
    /**
     * Creates a new teacher.
     * @param name       the name of the teacher to create
     * @param email      the email address of the teacher
     * @param department the department to which the teacher belongs
     * @param sections   the list of sections taught by the teacher
     * @return ResponseEntity<Object> containing the created teacher
     */
    ResponseEntity<Object> createTeacher(String name, String email, Department department, List<Section> sections);
    /**
     * Edits an existing teacher.
     * @param id         the ID of the teacher to edit
     * @param name       the new name of the teacher
     * @param email      the new email address of the teacher
     * @param department the new department to which the teacher belongs
     * @param sections   the new list of sections taught by the teacher
     * @return ResponseEntity<Object> containing the updated teacher if successful,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<Object> editTeacher(int id, String name, String email, Department department, List<Section> sections);
    /**
     * Deletes a teacher by their ID.
     * @param id the ID of the teacher to delete
     * @return ResponseEntity<Object> with status 200 if successful,
     *         or a 404 response if the teacher does not exist
     */
    ResponseEntity<Object> deleteTeacher(int id);
}
