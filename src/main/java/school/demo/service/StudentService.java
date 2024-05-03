package school.demo.service;

import org.springframework.http.ResponseEntity;
import school.demo.model.Section;

import java.util.List;
/**
 * This interface represents the definition of the Services for the class Student
 * It includes GET, POST, PUT, DELETE
 * The body definition for each method is listed on StudentServiceImplementation
 * @see school.demo.service.Implementation.StudentServiceImplementation
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
public interface StudentService {
    /**
     * Retrieves a student by their ID.
     * @param student_id the ID of the student to retrieve
     * @return ResponseEntity<Object> containing the student if found,
     *         or a 404 response if the student does not exist
     */
    ResponseEntity<Object> getStudentByID(int student_id);
    /**
     * Retrieves a student by their email.
     * @param student_email the email address of the student
     * @return ResponseEntity<Object> containing the student if found,
     *         or a 404 response if the student does not exist
     */
    ResponseEntity<Object> getStudentByEmail(String student_email);
    /**
     * Retrieves all students.
     * @return ResponseEntity<Object> containing a list of students
     */
    ResponseEntity<Object> getStudents();
    /**
     * Retrieves students by name.
     * @param student_name the name of the student
     * @return ResponseEntity<Object> containing a list of students with the specified name
     */
    ResponseEntity<Object> getStudentsByName(String student_name);
    /**
     * Creates a new student.
     * @param student_name  the name of the student to create
     * @param student_email the email address of the student
     * @param sections      the list of sections in which the student is enrolled
     * @return ResponseEntity<Object> containing the created student
     */
    ResponseEntity<Object> createStudent(String student_name, String student_email, List<Section> sections);
    /**
     * Edits an existing student.
     * @param student_id    the ID of the student to edit
     * @param student_name  the new name of the student
     * @param student_email the new email address of the student
     * @param sections      the new list of sections in which the student is enrolled
     * @return ResponseEntity<Object> containing the updated student if successful,
     *         or a 404 response if the student does not exist
     */
    ResponseEntity<Object> editStudent(int student_id, String student_name, String student_email, List<Section> sections);
    /**
     * Deletes a student by their ID.
     * @param student_id the ID of the student to delete
     * @return ResponseEntity<Object> with status 200 if successful,
     *         or a 404 response if the student does not exist
     */
    ResponseEntity<Object> deleteStudent(int student_id);
}
