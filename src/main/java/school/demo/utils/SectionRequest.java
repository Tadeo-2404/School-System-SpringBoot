package school.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;

@Data
@AllArgsConstructor
public class SectionRequest {
    private final Section section;
    private final Department department;
    private final Teacher teacher;
}
