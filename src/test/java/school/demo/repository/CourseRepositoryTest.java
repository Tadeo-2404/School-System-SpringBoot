package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CourseRepositoryTest {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private Course course;
    private Department department;

    @Autowired
    public CourseRepositoryTest(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    @BeforeEach
    public void setup() {
        this.department = new Department(TestConstants.DEPARTMENT_NAME);
        this.departmentRepository.save(department);
        this.course = new Course(TestConstants.COURSE_NAME, department);
        this.courseRepository.save(course);
    }

    @Test
    @Tag("Course_findByName")
    @Tag("Course_exist")
    @Tag("Exist")
    public void findByName_ShouldReturnCorrectEntity_WhenNameExist() {
        //Given
        String givenName = TestConstants.COURSE_NAME;

        //Then
        Optional<Course> courseTest = courseRepository.findByName(givenName);

        //When
        Assertions.assertTrue(courseTest.isPresent(), MessageConstants.COURSE_NOT_PRESENT_MESSAGE);
        Assertions.assertEquals(givenName, courseTest.get().getName(), MessageConstants.COURSE_NAME_NOT_MATCH_MESSAGE);
    }

    @Test
    @Tag("Course_findByName")
    @Tag("Course_not_exist")
    @Tag("Not_Exist")
    public void findByName_ShouldReturnEmptyEntity_WhenNameNotExist() {
        //Given
        String givenName = TestConstants.COURSE_NAME_NOT_EXIST;

        //When
        Optional<Course> courseTest = courseRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(courseTest.isEmpty(), MessageConstants.COURSE_NOT_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Course_findByName")
    @Tag("Course_null")
    @Tag("Null")
    public void findByName_ShouldReturnEmptyEntity_WhenNameIsNull() {
        //Given
        String givenName = TestConstants.NULL_STRING_VALUE;

        //When
        Optional<Course> courseTest = courseRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(courseTest.isEmpty(), MessageConstants.COURSE_NOT_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Course_findByDepartmentId")
    @Tag("Course_exist")
    @Tag("Exist")
    public void findByDepartmentId_ShouldReturnList_WhenDepartmentIdExist() {
        //Given
        Integer givenDepartmentId = this.department.getId();

        //When
        List<Course> list = courseRepository.findByDepartmentId(givenDepartmentId);

        //Then
        Assertions.assertNotNull(list, MessageConstants.EMPTY_LIST_MESSAGE);
        Assertions.assertEquals(TestConstants.COURSE_NAME, list.get(0).getName(), MessageConstants.COURSE_NAME_NOT_MATCH_MESSAGE);
    }

    @Test
    @Tag("Course_findByDepartmentId")
    @Tag("Course_not_exist")
    @Tag("Not_Exist")
    public void findByDepartmentId_ShouldReturnEmptyList_WhenDepartmentIdNotExist() {
        //Given
        Integer givenDepartmentId = TestConstants.COURSE_ID_NOT_EXIST;

        //When
        List<Course> list = courseRepository.findByDepartmentId(givenDepartmentId);

        //Then
        Assertions.assertTrue(list.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Course_findByDepartmentId")
    @Tag("Course_null")
    @Tag("Null")
    public void findByDepartmentId_ShouldReturnNullPointerException_WhenDepartmentIdIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            courseRepository.findByDepartmentId(TestConstants.NULL_INT_VALUE);
        });
    }

    @AfterEach
    public void teardown() {
        this.courseRepository.deleteAll();
        this.departmentRepository.deleteAll();
        this.course = null;
        this.department = null;
    }
}
