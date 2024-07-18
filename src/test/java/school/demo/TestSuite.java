package school.demo;

import org.junit.platform.suite.api.*;
import school.demo.repository.*;
import school.demo.service.DepartmentServiceTest;

@Suite
@SelectClasses({CourseRepositoryTest.class,
        DepartmentRepositoryTest.class,
        StudentRepositoryTest.class,
        SectionRepositoryTest.class,
        TeacherRepositoryTest.class,
        DepartmentServiceTest.class})
public class TestSuite {
}
