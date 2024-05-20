package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Department;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DepartmentRepositoryTest {
    private final DepartmentRepository departmentRepository;
    private Department department;

    @Autowired
    public DepartmentRepositoryTest(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @BeforeEach
    public void setup() {
        department = new Department(TestConstants.DEPARTMENT_NAME);
        departmentRepository.save(department);
    }

    @Test
    @Tag("Exist")
    public void findByName_ShouldReturnCorrectEntity_WhenNameExist() {
        //Given
        String givenName = TestConstants.DEPARTMENT_NAME;

        //When
        Optional<Department> optionalDepartment = departmentRepository.findByName(givenName);

        //Then
        Assertions.assertNotNull(optionalDepartment, MessageConstants.DEPARTMENT_IS_NULL);
    }

    @Test
    @Tag("Not_Exist")
    public void findByName_ShouldReturnEmptyEntity_WhenNameNotExist() {
        //Given
        String givenName = TestConstants.DEPARTMENT_NAME_NOT_EXIST;

        //When
        Optional<Department> optionalDepartment = departmentRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(optionalDepartment.isEmpty(), MessageConstants.DEPARTMENT_NOT_NULL);
    }

    @Test
    @Tag("Null")
    public void findByName_ShouldReturnEmptyEntity_WhenNameIsNull() {
        //Given
        String givenName = TestConstants.NULL_STRING_VALUE;

        //When
        Optional<Department> optionalDepartment = departmentRepository.findByName(givenName);

        //Then
        Assertions.assertTrue(optionalDepartment.isEmpty(), MessageConstants.DEPARTMENT_NOT_NULL);
    }

    @AfterEach
    public void teardown() {
        this.departmentRepository.deleteAll();
        this.department = null;
    }
}
