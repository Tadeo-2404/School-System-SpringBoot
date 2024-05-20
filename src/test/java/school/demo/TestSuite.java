package school.demo;

import org.junit.platform.suite.api.*;
import school.demo.repository.*;

@Suite
@SelectPackages("school.demo.repository")
@SelectClasses({CourseRepositoryTest.class,
        DepartmentRepositoryTest.class,
        StudentRepositoryTest.class,
        SectionRepositoryTest.class,
        TeacherRepositoryTest.class})
public class TestSuite {
}
