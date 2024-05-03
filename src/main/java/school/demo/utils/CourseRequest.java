package school.demo.utils;
import school.demo.model.Department;
import school.demo.model.Course;

/**
 * This record represents a CourseRequest, combining a course and its associated department.
 * It is used for handling requests related to courses.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record CourseRequest(Course course, Department department) {
}
