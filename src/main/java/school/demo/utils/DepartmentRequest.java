package school.demo.utils;
import school.demo.model.Course;
import school.demo.model.Department;
import java.util.List;

/**
 * This record represents a DepartmentRequest, combining a list of courses and a department.
 * It is used for handling requests related to departments.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record DepartmentRequest(List<Course> courses, Department department){
}
