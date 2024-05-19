package school.demo;

import org.junit.platform.suite.api.*;
import school.demo.repository.SectionRepositoryTest;
import school.demo.repository.TeacherRepositoryTest;

@Suite
@SelectPackages("school.demo.repository")
@SelectClasses({SectionRepositoryTest.class, TeacherRepositoryTest.class})
@IncludeTags("Exist")
public class TestSuite {
}
