package school.demo.utils;

import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import java.util.List;

/**
 * This record represents a TeacherRequest, combining teacher details with their associated department and sections taught.
 * It is used for handling requests related to teachers.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record TeacherRequest(Teacher teacher, Department department)
{}
