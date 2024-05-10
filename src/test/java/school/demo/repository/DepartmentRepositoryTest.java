package school.demo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Department;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    private Department department;

    @BeforeEach
    public void setup() {
        department = new Department("QA");
        departmentRepository.save(department);
    }

    @Test()
    public void findAllTest() {
        List<Department> list = departmentRepository.findAll();
        Assertions.assertFalse(list.isEmpty(), "List is empty");
    }

    @Test
    public void findByNameTestFound() {
        Optional<Department> testDepartment = departmentRepository.findByName("QA");
        Assertions.assertNotNull(testDepartment, "Department is null");
    }

    @Test
    public void findByNameTestNotFound() {
        Optional<Department> testDepartment = departmentRepository.findByName("NOT_FOUND");
        Assertions.assertNotNull(testDepartment, "Department is not null");
    }

    @Test
    public void findByIdTestFound() {
        Optional<Department> testDepartment = departmentRepository.findById(1);
        Assertions.assertNotNull(testDepartment, "Department is not null");
    }

    @Test
    public void findByIdTestNotFound() {
        Optional<Department> testDepartment = departmentRepository.findById(2);
        Assertions.assertNotNull(testDepartment, "Department is not null");
    }

    @AfterEach
    public void teardown() {
        this.departmentRepository.deleteAll();
        this.departmentRepository = null;
        this.department = null;
    }
}
