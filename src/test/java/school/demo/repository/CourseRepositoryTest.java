package school.demo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Course;
import school.demo.model.Department;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    private Course course;
    private Course courseSecond;
    private Department department;

    @BeforeEach
    public void setup() {
        this.department = new Department("Department");
        this.departmentRepository.save(department);
        this.course = new Course("Course", department);
        this.courseSecond = new Course("CourseSecond", department);
        this.courseRepository.save(course);
        this.courseRepository.save(courseSecond);
    }

    @Test
    public void findByNameFoundTest() {
        Optional<Course> courseTest = courseRepository.findByName("Course");
        Assertions.assertTrue(courseTest.isPresent(), "Course is not present");
        Assertions.assertEquals("Course", courseTest.get().getName(), "Names do not match");
    }

    @Test
    public void findByNameNotFoundTest() {
        Optional<Course> courseTest = courseRepository.findByName("CourseNotFound");
        Assertions.assertTrue(courseTest.isEmpty(), "Course is present");
    }

    @Test
    public void findByDepartmentIdFoundTest() {
        List<Course> list = courseRepository.findByDepartmentId(department.getId());
        Assertions.assertEquals(2, list.size(), "List size does not match");
        Assertions.assertEquals("Course", list.get(0).getName(), "Registry name does not match");
    }

    @Test
    public void findByDepartmentIdNotFoundTest() {
        List<Course> list = courseRepository.findByDepartmentId(2);
        Assertions.assertTrue(list.isEmpty(), "List size does not match");
    }

    @Test
    public void findByDepartmentNameFoundTest() {
        List<Course> list = courseRepository.findByDepartmentName(department.getName());
        Assertions.assertFalse(list.isEmpty(), "List is empty");
        Assertions.assertEquals("Course", list.get(0).getName(), "Registry name does not match");
    }

    @AfterEach
    public void teardown() {
        this.courseRepository.deleteAll();
        this.departmentRepository.deleteAll();
        this.course = null;
        this.department = null;
    }
}
