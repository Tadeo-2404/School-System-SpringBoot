package school.demo.utils;
import school.demo.model.*;
import java.util.List;

/**
 * This record represents a SectionRequest, combining section details with its associated students and identifiers for department, course, and teacher.
 * It is used for handling requests related to sections.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record SectionRequest(String section, List<Student> students, int departmentId, int courseId, int teacherId) {}
