package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Teacher;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setup() {
        this.teacherRepository.save(TestConstants.teacher);
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
        Assertions.assertTrue(teacherOptional.isPresent(), MessageConstants.TEACHER_NOT_PRESENT_MESSAGE);
        Assertions.assertEquals(givenEmail, teacherOptional.get().getEmail(), MessageConstants.EMAIL_NOT_MATCH_MESSAGE);
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
        Assertions.assertTrue(teacherOptional.isEmpty(), MessageConstants.TEACHER_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Teacher_findByEmail")
    @Tag("Teacher_null")
    @Tag("Null")
    public void findByEmail_ShouldReturnNoEntity_WhenEmailIsNull() {
        //Given //When
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(TestConstants.NULL_STRING_VALUE);

        //Then
        Assertions.assertTrue(teacherOptional.isEmpty(), MessageConstants.TEACHER_PRESENT_MESSAGE);
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
        Assertions.assertFalse(teacherList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
        Assertions.assertEquals(TestConstants.TEACHER_EMAIL, teacherList.get(0).getEmail(), MessageConstants.EMAIL_NOT_MATCH_MESSAGE);
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
        Assertions.assertTrue(teacherList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
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
        Assertions.assertTrue(teacherList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @AfterEach
    public void teardown() {
        this.teacherRepository.deleteAll();
    }
}
