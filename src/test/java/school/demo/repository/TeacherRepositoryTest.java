package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Department;
import school.demo.model.Teacher;
import school.demo.utils.TestMessageConstants;
import school.demo.utils.TestConstants;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeacherRepositoryTest {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private Department department;
    private Teacher teacher;

    @Autowired
    public TeacherRepositoryTest(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    @BeforeEach
    public void setup() {
        department = new Department(TestConstants.DEPARTMENT_NAME);
        this.departmentRepository.save(department);
        teacher = new Teacher(TestConstants.TEACHER_NAME, TestConstants.TEACHER_EMAIL, department);
        this.teacherRepository.save(teacher);
    }

    @Test
    @Tag("Teacher_findByEmail")
    @Tag("Teacher_exist")
    @Tag("Exist")
    public void findByEmail_ShouldReturnEntity_WhenEmailExists() {
        //Given
        String givenEmail = TestConstants.TEACHER_EMAIL;

        //When
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(givenEmail);

        //Then
        Assertions.assertTrue(teacherOptional.isPresent(), TestMessageConstants.TEACHER_NOT_PRESENT_MESSAGE);
        Assertions.assertEquals(givenEmail, teacherOptional.get().getEmail(), TestMessageConstants.EMAIL_NOT_MATCH_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByEmail")
    @Tag("Teacher_not_exist")
    @Tag("Not_Exist")
    public void findByEmail_ShouldReturnNoEntity_WhenEmailNoExists() {
        //Given
        String givenEmail = TestConstants.TEACHER_EMAIL_NOT_EXIST;

        //When
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(givenEmail);

        //Then
        Assertions.assertTrue(teacherOptional.isEmpty(), TestMessageConstants.TEACHER_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByEmail")
    @Tag("Teacher_null")
    @Tag("Null")
    public void findByEmail_ShouldReturnNoEntity_WhenEmailIsNull() {
        //Given //When
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(TestConstants.NULL_STRING_VALUE);

        //Then
        Assertions.assertTrue(teacherOptional.isEmpty(), TestMessageConstants.TEACHER_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByName")
    @Tag("Teacher_exist")
    @Tag("Exist")
    public void findByName_ShouldReturnList_WhenNameExist() {
        //Given
        String givenName = TestConstants.TEACHER_NAME;

        //When
        List<Teacher> teacherList = teacherRepository.findByName(givenName);

        //Then
        Assertions.assertFalse(teacherList.isEmpty(), TestMessageConstants.EMPTY_LIST_MESSAGE);
        Assertions.assertEquals(TestConstants.TEACHER_EMAIL, teacherList.get(0).getEmail(), TestMessageConstants.EMAIL_NOT_MATCH_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByName")
    @Tag("Teacher_not_exist")
    @Tag("Not_Exist")
    public void findByName_ShouldReturnEmptyList_WhenNameNoExist() {
        //Given
        String givenName = TestConstants.TEACHER_NAME_NOT_EXIST;

        //When
        List<Teacher> teacherList = teacherRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(teacherList.isEmpty(), TestMessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByName")
    @Tag("Teacher_null")
    @Tag("Null")
    public void findByNameNullTest() {
        //Given
        String givenName = TestConstants.NULL_STRING_VALUE;

        //When
        List<Teacher> teacherList = teacherRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(teacherList.isEmpty(), TestMessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @AfterEach
    public void teardown() {
        this.department = null;
        this.teacher = null;
        this.teacherRepository.deleteAll();
    }
}
