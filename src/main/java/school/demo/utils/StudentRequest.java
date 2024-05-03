package school.demo.utils;

import school.demo.model.Section;
import school.demo.model.Student;
import java.util.List;

/**
 * This record represents a StudentRequest, combining student details with a list of sections they are enrolled in.
 * It is used for handling requests related to students.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record StudentRequest (Student student, List<Section> sections) {}
