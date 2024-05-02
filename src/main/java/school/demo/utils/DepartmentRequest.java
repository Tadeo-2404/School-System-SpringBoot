package school.demo.utils;
import school.demo.model.Course;
import school.demo.model.Department;
import java.util.List;

public record DepartmentRequest(List<Course> courses, Department department){
}
