package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Student;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {
    private final StudentRepository studentRepository;
    private Student student;

    @Autowired
    public StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @BeforeEach
    public void setup() {
        this.student = new Student(TestConstants.STUDENT_NAME, TestConstants.STUDENT_EMAIL);
        this.studentRepository.save(student);
    }

    @Test
    @Tag("Student_findByEmail")
    @Tag("Student_exist")
    @Tag("Exist")
    public void findByEmail_ShouldReturnCorrectEntity_WhenEmailExist() {
        //Given
        String givenEmail = TestConstants.STUDENT_EMAIL;

        //When
        Optional<Student> studentTest = studentRepository.findByEmail(givenEmail);

        //Then
        Assertions.assertNotNull(studentTest, MessageConstants.STUDENT_IS_NULL);
    }

    @Test
    @Tag("Student_findByEmail")
    @Tag("Student_not_exist")
    @Tag("Not_Exist")
    public void findByEmail_ShouldReturnNoEntity_WhenEmailNotExist() {
        //Given
        String givenEmail = TestConstants.STUDENT_EMAIL_NOT_EXIST;

        //When
        Optional<Student> studentOptional = studentRepository.findByEmail(givenEmail);

        //Then
        Assertions.assertEquals(Optional.empty(), studentOptional, MessageConstants.STUDENT_NOT_NULL);
    }

    @Test
    @Tag("Student_findByEmail")
    @Tag("Student_null")
    @Tag("Null")
    public void findByEmail_ShouldReturnNoEntity_WhenEmailIsNull() {
        //Given
        String givenEmail = TestConstants.NULL_STRING_VALUE;

        //When
        Optional<Student> studentTest = studentRepository.findByEmail(givenEmail);

        //Then
        Assertions.assertEquals(Optional.empty() ,studentTest, MessageConstants.STUDENT_NOT_NULL);
    }

    @Test
    @Tag("Student_findByName")
    @Tag("Student_exist")
    @Tag("Exist")
    public void findByName_ShouldReturnList_WhenNameExist() {
        //Given
        String givenName = TestConstants.STUDENT_NAME;

        //When
        List<Student> studentList = studentRepository.findByName(givenName);

        //Then
        Assertions.assertFalse(studentList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Student_findByName")
    @Tag("Student_not_exist")
    @Tag("Not_Exist")
    public void findByName_ShouldReturnEmptyList_WhenNameNotExist() {
        //Given
        String givenName = TestConstants.STUDENT_NAME_NOT_EXIST;

        //When
        List<Student> studentList = studentRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(studentList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Student_findByName")
    @Tag("Student_null")
    @Tag("Null")
    public void findByName_ShouldReturnEmptyList_WhenNameIsNull() {
        //Given
        String givenName = TestConstants.NULL_STRING_VALUE;

        //When
        List<Student> studentList = studentRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(studentList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @AfterEach
    public void teardown() {
        this.student = null;
        this.studentRepository.deleteAll();
    }
}
