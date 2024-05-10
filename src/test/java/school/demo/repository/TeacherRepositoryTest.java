package school.demo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Teacher;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    private Teacher teacher;

    @BeforeEach
    public void setup() {
        this.teacher = new Teacher("John", "email@email.com");
        this.teacherRepository.save(teacher);
    }

    @Test
    public void findByEmailFoundTest() {
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail("email@email.com");
        Assertions.assertTrue(teacherOptional.isPresent(), "Teacher is not present");
        Assertions.assertEquals("email@email.com", teacherOptional.get().getEmail(), "Email does not match");
    }

    @Test
    public void findByEmailNotFoundTest() {
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail("email@email2.com");
        Assertions.assertTrue(teacherOptional.isEmpty(), "Teacher is present");
    }

    @Test
    public void findByEmailNullTest() {
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(null);
        Assertions.assertTrue(teacherOptional.isEmpty(), "Teacher is present");
    }


    @Test
    public void findByNameFoundTest() {
        List<Teacher> teacherList = teacherRepository.findByName("John");
        Assertions.assertFalse(teacherList.isEmpty(), "TeacherList is empty");
        Assertions.assertEquals("email@email.com", teacherList.get(0).getEmail(), "Email does not match");
    }

    @Test
    public void findByNameNotFoundTest() {
        List<Teacher> teacherList = teacherRepository.findByName("NotFound");
        Assertions.assertTrue(teacherList.isEmpty(), "TeacherList is not empty");
    }

    @Test
    public void findByNameNullTest() {
        List<Teacher> teacherList = teacherRepository.findByName(null);
        Assertions.assertTrue(teacherList.isEmpty(), "TeacherList is not empty");
    }

    @AfterEach
    public void teardown() {
        this.teacher = null;
        this.teacherRepository.deleteAll();
    }
}
