package school.demo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Student;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;
    private Student student;

    @BeforeEach
    public void setup() {
        this.student = new Student("tadeo", "email@email.com");
        this.studentRepository.save(student);
    }

    @Test
    public void findByEmailFoundTest() {
        Optional<Student> studentTest = studentRepository.findByEmail("email@email.com");
        Assertions.assertNotNull(studentTest, "Student is null");
    }

    @Test
    public void findByEmailNotFoundTest() {
        Optional<Student> studentTest = studentRepository.findByEmail("email@email2.com");
        Assertions.assertEquals(Optional.empty() ,studentTest, "Student is not empty");
    }

    @Test
    public void findByEmailNullTest() {
        Optional<Student> studentTest = studentRepository.findByEmail(null);
        Assertions.assertEquals(Optional.empty() ,studentTest, "Student is not empty");
    }

    @Test
    public void findByNameFoundTest() {
        List<Student> studentTest = studentRepository.findByName("tadeo");
        Assertions.assertEquals(1, studentTest.size(), "Student is null");
    }

    @Test
    public void findByNameNotFoundTest() {
        List<Student> studentTest = studentRepository.findByName("sebastian");
        Assertions.assertTrue(studentTest.isEmpty(), "Student is not null");
    }

    @Test
    public void findByNameNullTest() {
        List<Student> studentTest = studentRepository.findByName(null);
        Assertions.assertTrue(studentTest.isEmpty(), "Student is not empty");
    }

    @AfterEach
    public void teardown() {
        this.student = null;
        this.studentRepository.deleteAll();
    }
}
