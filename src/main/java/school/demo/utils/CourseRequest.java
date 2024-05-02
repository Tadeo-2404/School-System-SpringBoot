package school.demo.utils;
import school.demo.model.Department;
import school.demo.model.Course;

public record CourseRequest(Course course, Department department) {
}
