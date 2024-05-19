package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SectionRepositoryTest {
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private Section section;
    private Department department;
    private Course course;
    private Teacher teacher;

    @Autowired
    public SectionRepositoryTest(SectionRepository sectionRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @BeforeEach
    public void setup() {
        department = new Department("department");
        departmentRepository.save(department);
        teacher = new Teacher("teacher", "email", department);
        teacherRepository.save(teacher);
        course = new Course("course", department);
        courseRepository.save(course);
        section = new Section("section01", department, course, teacher);
        sectionRepository.save(section);
    }

    @Test
    @Tag("Section_findByName")
    public void findByName_ShouldReturnCorrectEntity_WhenNameExists() {
        //Given
        String name = "section01";

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName("section01");

        //Then
        Assertions.assertEquals("section01", sectionOptional.get().getName(), "Section is not present");
    }

    @Test
    @Tag("Section_findByName")
    public void findByName_ShouldReturnsEmptyResult_WhenNameNotExists() {
        //Given
        String name = "section001";

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName("section001");

        //Then
        Assertions.assertTrue(sectionOptional.isEmpty(), "Section is present");
    }

    @Test
    @Tag("Section_findByName")
    public void findByName_ShouldReturnsEmptyResult_WhenNameIsNull() {
        //Given
        String name = null;

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName(name);

        //Then
        Assertions.assertTrue(sectionOptional.isEmpty(), "Section is present");
    }

    @Test
    @Tag("Section_findByDepartmentId")
    public void findByDepartmentId_ShouldReturnCorrectEntity_WhenIdExists() {
        //Given
        int id = department.getId();

        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(id);

        //Then
        Assertions.assertNotNull(sectionList, "sectionList is empty");
    }

    @Test
    @Tag("Section_findByDepartmentId")
    public void findByDepartmentId_ShouldReturnEmptyEntity_WhenIdNotExists() {
        //Given
        int id = 2;

        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(id);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @Test
    @Tag("Section_findByDepartmentId")
    public void findByDepartmentId_ShouldReturnEmptyList_WhenIdIsNull() {
        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(null);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @Test
    @Tag("Section_findByTeacherId")
    public void findByTeacherId_ShouldReturnList_WhenIdExists() {
        //Given
        int givenId = 1;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(givenId);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), "sectionList is empty");
    }

    @Test
    @Tag("Section_findByTeacherId")
    public void findByTeacherId_ShouldReturnEmpty_WhenIdNoExists() {
        //Given
        int givenId = 2;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(givenId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList should be empty");
    }

    @Test
    @Tag("Section_findByTeacherId")
    public void findByTeacherId_ShouldReturnEmpty_WhenIdIsNull() {
        //Given
        Integer id = null;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(id);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList should be empty");
    }

    @Test
    @Tag("Section_findByTeacherName")
    public void findByTeacherName_ShouldReturnList_WhenNameExists() {
        //Given
        String name = "teacher";

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(name);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), "sectionList should not be empty");
    }

    @Test
    @Tag("Section_findByTeacherName")
    public void findByTeacherName_ShouldReturnEmptyList_WhenNameNotExists() {
        //Given
        String name = "teacherNotExist";

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(name);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList should be empty");
    }

    @Test
    @Tag("Section_findByTeacherName")
    public void findByTeacherName_ShouldReturnEmptyList_WhenNameIsNull() {
        //Given
        String name = null;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(name);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList should be empty");
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    public void findByTeacherEmail_ShouldReturnList_WhenEmailExists() {
        //Given
        String givenEmail = "email";

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), "sectionList is empty");
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    public void findByTeacherEmail_ShouldReturnEmptyList_WhenEmailNotExists() {
        //Given
        String givenEmail = "emailNotExist@email.com";

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    public void findByTeacherEmail_ShouldReturnEmptyList_WhenEmailIsNull() {
        //Given
        String givenEmail = null;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @Test
    @Tag("Section_findByCourseId")
    public void findByCourseId_ShouldReturnList_WhenCourseIdExists() {
        //Given
        Integer courseId = 1;

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(courseId);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), "sectionList is empty");
        Assertions.assertEquals(1, sectionList.get(0).getCourse().getId(), "courseId does not match");
    }

    @Test
    @Tag("Section_findByCourseId")
    public void findByCourseId_ShouldReturnEmptyList_WhenCourseIdNotExists() {
        //Given
        Integer courseId = 2;

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(courseId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @Test
    @Tag("Section_findByCourseId")
    public void findByCourseId_ShouldReturnEmptyList_WhenCourseIdIsNull() {
        //Given
        Integer courseId = null;

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(courseId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), "sectionList is not empty");
    }

    @AfterEach
    public void teardown() {
        department = null;
        teacher = null;
        course = null;
        section = null;
        departmentRepository.deleteAll();
        teacherRepository.deleteAll();
        courseRepository.deleteAll();
        sectionRepository.deleteAll();
    }
}
