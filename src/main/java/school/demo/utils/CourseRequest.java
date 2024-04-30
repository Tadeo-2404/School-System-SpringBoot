package school.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import school.demo.model.Department;
import school.demo.model.Course;

@Data
@AllArgsConstructor
public class CourseRequest {
    private Course course;
    private Department department;
}
