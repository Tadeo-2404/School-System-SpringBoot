package school.demo.utils;

import school.demo.model.Section;
import school.demo.model.Student;
import java.util.List;

public record StudentRequest (Student student, List<Section> sections) {}
