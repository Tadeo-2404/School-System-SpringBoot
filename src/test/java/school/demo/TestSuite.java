package school.demo;

import org.junit.platform.suite.api.*;
import school.demo.repository.SectionRepositoryTest;
import school.demo.repository.TeacherRepositoryTest;

@Suite
@SelectPackages("school.demo.repository")
@SelectClasses({SectionRepositoryTest.class, TeacherRepositoryTest.class})
@IncludeTags("Section_findByName")
@ExcludeTags("Section_findByDepartmentId")
public class TestSuite {
}
