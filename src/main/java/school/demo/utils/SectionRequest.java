package school.demo.utils;
import school.demo.model.*;
import java.util.List;

public record SectionRequest(String section, List<Student> students, int departmentId, int courseId, int teacherId) {}
