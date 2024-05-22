package school.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
import school.demo.repository.DepartmentRepository;
import school.demo.service.Implementation.DepartmentServiceImplementation;
import school.demo.utils.CustomResponse;
import school.demo.utils.TestConstants;

import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImplementation departmentServiceImplementation;

    private Department department;

    @BeforeEach
    public void setup() {
        // Initialize the Department object with an ID and name
        department = new Department(1, TestConstants.DEPARTMENT_NAME);
    }

    @Test
    public void getDepartmentsById_ShouldReturn200_WhenIdExist() {
        // Arrange
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(department.getId());

        // Assert
        Assertions.assertNotNull(responseEntity.getBody(), "Response body should not be null");
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getBody().getStatusCode(), "Status code does not match");
        Assertions.assertEquals(Optional.of(department), responseEntity.getBody().getData(), "Returned department does not match");
    }

    @Test
    public void getDepartmentsById_ShouldReturn404_WhenIdNotExist() {
        // Arrange
        int nonExistentDepartmentId = TestConstants.DEPARTMENT_ID_NOT_EXIST;
        Mockito.when(departmentRepository.findById(nonExistentDepartmentId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(nonExistentDepartmentId);

        // Assert
        Assertions.assertNotNull(responseEntity.getBody(), "Response body should not be null");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode(), "Status code does not match");
    }
}
