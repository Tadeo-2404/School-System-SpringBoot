package school.demo.service;
import org.springframework.http.ResponseEntity;
/**
 * This interface represents the definition of the Services for the class Section
 * It includes GET, POST, PUT, DELETE
 * The body definition for each method is listed on SectionServiceImplementation
 * @see school.demo.service.Implementation.SectionServiceImplementation
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
public interface SectionService {
    /**
     * Retrieves all sections.
     * @return ResponseEntity<Object> containing a list of sections
     */
    ResponseEntity<Object> getSections();
    /**
     * Retrieves a section by its ID.
     * @param id the unique identifier of the section
     * @return ResponseEntity<Object> containing the section if found,
     *         or a 404 response if the section does not exist
     */
    ResponseEntity<Object> getSectionById(Integer id);
    /**
     * Retrieves sections by name.
     * @param name the name of the section
     * @return ResponseEntity<Object> containing a list of sections with the specified name
     */
    ResponseEntity<Object> getSectionByName(String name);
    /**
     * Retrieves sections by department ID.
     * @param departmentId the ID of the department
     * @return ResponseEntity<Object> containing a list of sections belonging to the specified department
     */
    ResponseEntity<Object> getSectionsByDepartmentId(Integer departmentId);
    /**
     * Retrieves sections by department name.
     * @param name the name of the department
     * @return ResponseEntity<Object> containing a list of sections belonging to the department with the specified name
     */
    ResponseEntity<Object> getSectionsByDepartmentName(String name);
    /**
     * Retrieves sections by teacher ID.
     * @param teacherId the ID of the teacher
     * @return ResponseEntity<Object> containing a list of sections taught by the specified teacher
     */
    ResponseEntity<Object> getSectionsByTeacherId(Integer teacherId);
    /**
     * Retrieves sections by teacher name.
     * @param name the name of the teacher
     * @return ResponseEntity<Object> containing a list of sections taught by the teacher with the specified name
     */
    ResponseEntity<Object> getSectionsByTeacherName(String name);
    /**
     * Retrieves sections by teacher email.
     * @param email the email address of the teacher
     * @return ResponseEntity<Object> containing a list of sections taught by the teacher with the specified email address
     */
    ResponseEntity<Object> getSectionsByTeacherEmail(String email);
    /**
     * Creates a new section.
     * @param name         the name of the section to create
     * @param departmentId the ID of the department to which the section belongs
     * @param courseId     the ID of the course associated with the section
     * @param teacherId    the ID of the teacher teaching the section
     * @return ResponseEntity<Object> containing the created section
     */
    ResponseEntity<Object> createSection(String name, Integer departmentId, Integer courseId, Integer teacherId);

    /**
     * Edits an existing section.
     * @param id           the unique identifier of the section to edit
     * @param name         the new name of the section
     * @param departmentId the new ID of the department to which the section belongs
     * @param courseId     the new ID of the course associated with the section
     * @param teacherId    the new ID of the teacher teaching the section
     * @return ResponseEntity<Object> containing the updated section if successful,
     *         or a 404 response if the section does not exist
     */
    ResponseEntity<Object> editSection(Integer id, String name, Integer departmentId, Integer courseId, Integer teacherId);
    /**
     * Deletes a section by its ID.
     * @param id the unique identifier of the section to delete
     * @return ResponseEntity<Object> with status 200 if successful,
     *         or a 404 response if the section does not exist
     */
    ResponseEntity<Object> deleteSection(Integer id);
}
