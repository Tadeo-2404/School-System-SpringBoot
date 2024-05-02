package school.demo.utils;

import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import java.util.List;

public record TeacherRequest(Teacher teacher, Department department,List<Section> sections)
{}
