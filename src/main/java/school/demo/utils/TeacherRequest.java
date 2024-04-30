package school.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import school.demo.model.Department;
import school.demo.model.Teacher;

@Data
@AllArgsConstructor
public class TeacherRequest {
    private Teacher teacher;
    private Department department;
}
