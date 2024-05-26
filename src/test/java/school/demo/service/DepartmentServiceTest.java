package school.demo.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.demo.model.Department;
import school.demo.repository.DepartmentRepository;
import school.demo.service.Implementation.DepartmentServiceImplementation;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;
import school.demo.utils.TestMessageConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest extends BaseServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImplementation departmentServiceImplementation;

    private Department department;

    @BeforeEach
    public void setupDepartment() {
        department = new Department(1, TestConstants.DEPARTMENT_NAME);
    }

    @AfterEach
    public void teardownDepartment() {
        department = null;
    }

    @Test
    public void getDepartments_ShouldReturn200_WhenRegistriesExist() {
        // Arrange
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        when(departmentRepository.findAll()).thenReturn(departmentList);

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartments();

        //Verify
        verify(departmentRepository, times(1)).findAll();

        // Assert
        assertNotNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_NOT_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartments_ShouldReturn404_WhenNoRegistriesExist() {
        //Arrange
        List<Department> departmentList = new ArrayList<>();
        when(departmentRepository.findAll()).thenReturn(departmentList);

        //Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartments();

        //Verify
        verify(departmentRepository, times(1)).findAll();

        //Assert
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsById_ShouldReturn200_WhenIdExist() {
        //Arrange
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        //Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(department.getId());

        //Verify
        verify(departmentRepository, times(1)).findById(department.getId());

        //Assert
        assertNotNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_NOT_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.OK.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(Optional.of(department), responseEntity.getBody().getData(), TestMessageConstants.DEPARTMENT_OBJECT_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsById_ShouldReturn404_WhenIdNotExist() {
        //Arrange
        int nonExistentDepartmentId = TestConstants.DEPARTMENT_ID_NOT_EXIST;

        //Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(nonExistentDepartmentId);

        //Assert
        assertNotNull(responseEntity.getBody(), "Response body should not be null");
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode(), "Status code does not match");
    }

    @Test
    public void getDepartmentsById_ShouldReturn400_WhenIdIs0() {
        // Arrange
        Integer nullId = 0;

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(nullId);

        // Assert
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsById_ShouldReturn400_WhenIdIsNull() {
        // Arrange
        Integer nullId = null;

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsById(nullId);

        // Assert
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsByName_ShouldReturn200_WhenNameExist() {
        String givenName = department.getName();
        when(departmentRepository.findByName(givenName)).thenReturn(Optional.of(department));

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsByName(givenName);

        //Verify
        verify(departmentRepository, times(1)).findByName(givenName);

        // Assert
        assertNotNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_NOT_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.OK.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsByName_ShouldReturn404_WhenNameNoExist() {
        String givenName = TestConstants.DEPARTMENT_NAME_NOT_EXIST;

        when(departmentRepository.findByName(givenName)).thenReturn(Optional.empty());

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsByName(givenName);

        //Verify
        verify(departmentRepository, times(1)).findByName(givenName);

        // Assert
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getDepartmentsByName_ShouldReturn404_WhenNameIsNull() {
        String givenName = null;

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.getDepartmentsByName(givenName);

        // Assert
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getBody().getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void createDepartment_ShouldReturn201_WhenDepartmentCreated() {
        //given
        String givenName = department.getName();
        Department departmentToSave = new Department(givenName);

        when(departmentRepository.save(departmentToSave)).thenReturn(departmentToSave);

        //when
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.createDepartment(givenName);
        verify(departmentRepository, times(1)).save(departmentToSave);

        //then
        assertEquals(HttpStatus.CREATED, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(departmentToSave, responseEntity.getBody().getData(), TestMessageConstants.SAVED_OBJECT_NOT_MATCH);
    }

    @Test
    public void createDepartment_ShouldReturn400_WhenNameIsNull() {
        //Given
        String givenName = null;

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.createDepartment(givenName);
        System.out.println(responseEntity);

        //Then
        verify(departmentRepository, never()).save(any(Department.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.MISSING_NAME_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void createDepartment_ShouldReturn400_WhenNameIsDuplicated() {
        //Given
        String givenName = department.getName();
        when(departmentRepository.findByName(givenName)).thenReturn(Optional.of(department));

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.createDepartment(givenName);

        //Then
        verify(departmentRepository, times(1)).findByName(givenName);
        verify(departmentRepository, never()).save(any(Department.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.DUPLICATE_NAME_ATTRIBUTE_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void createDepartment_ShouldReturn500_WhenExceptionThrown() {
        // Given
        String givenName = TestConstants.DEPARTMENT_NAME;
        when(departmentRepository.findByName(givenName)).thenThrow(new RuntimeException(TestMessageConstants.RUNTIME_EXCEPTION_MESSAGE));

        // When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.createDepartment(givenName);
        System.out.println(responseEntity);

        // Then
        verify(departmentRepository, times(1)).findByName(givenName);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(TestMessageConstants.RUNTIME_EXCEPTION_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void editDepartment_ShouldReturn200_WhenDepartmentIsEdited() {
        // Arrange
        String newDepartmentName = "New Name";
        int departmentId = department.getId();
        Department expectedDepartment = new Department(departmentId, newDepartmentName);

        when(departmentRepository.existsById(departmentId)).thenReturn(true);
        when(departmentRepository.findByName(newDepartmentName)).thenReturn(Optional.empty());
        when(departmentRepository.save(any(Department.class))).thenReturn(expectedDepartment);

        // Act
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.editDepartment(departmentId, newDepartmentName);
        System.out.println(responseEntity);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(expectedDepartment, responseEntity.getBody().getData(), TestMessageConstants.DEPARTMENT_OBJECT_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.DEPARTMENT_EDITED_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void editDepartment_ShouldReturn400_WhenIdIsNull() {
        Integer givenId = null;
        String givenName = TestConstants.DEPARTMENT_NAME;

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.editDepartment(givenId, givenName);

        verify(departmentRepository, never()).findByName(anyString());
        verify(departmentRepository, never()).existsById(anyInt());
        verify(departmentRepository, never()).save(any(Department.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void editDepartment_ShouldReturn400_WhenIdIs0() {
        Integer givenId = 0;
        String givenName = TestConstants.DEPARTMENT_NAME;

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.editDepartment(givenId, givenName);

        verify(departmentRepository, never()).findByName(anyString());
        verify(departmentRepository, never()).existsById(anyInt());
        verify(departmentRepository, never()).save(any(Department.class));
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void editDepartment_ShouldReturn400_WhenNameExist() {
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));
        Integer givenId = department.getId();
        String givenName = department.getName();

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.editDepartment(givenId, givenName);

        verify(departmentRepository, times(1)).findByName(givenName);
        verify(departmentRepository, never()).existsById(anyInt());
        verify(departmentRepository, never()).save(any(Department.class));
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void editDepartment_ShouldReturn404_WhenIdNotExist() {
        when(departmentRepository.existsById(department.getId())).thenReturn(false);
        Integer givenId = department.getId();
        String givenName = department.getName();

        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.editDepartment(givenId, givenName);

        verify(departmentRepository, times(1)).findByName(givenName);
        verify(departmentRepository, times(1)).existsById(givenId);
        verify(departmentRepository, never()).save(any(Department.class));
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void deleteDepartment_ShouldReturn200_WhenDepartmentDeleted() {
        //Given
        when(departmentRepository.existsById(department.getId())).thenReturn(true);

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.deleteDepartment(department.getId());

        //Then
        verify(departmentRepository, times(1)).existsById(department.getId());
        verify(departmentRepository, times(1)).deleteById(department.getId());
        assertEquals(HttpStatus.OK, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.DEPARTMENT_REMOVED_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void deleteDepartment_ShouldReturn400_WhenIdIsZero() {
        //Given
        Integer id = 0;

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.deleteDepartment(id);
        System.out.println(responseEntity);

        //Then
        verify(departmentRepository, never()).existsById(id);
        verify(departmentRepository, never()).deleteById(id);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void deleteDepartment_ShouldReturn400_WhenIdIsNull() {
        //Given
        Integer id = null;

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.deleteDepartment(id);
        System.out.println(responseEntity);

        //Then
        verify(departmentRepository, never()).existsById(id);
        verify(departmentRepository, never()).deleteById(id);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
        assertEquals(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void deleteDepartment_ShouldReturn404_WhenDepartmentNotExist() {
        //Given
        when(departmentRepository.existsById(department.getId())).thenReturn(false);

        //When
        ResponseEntity<CustomResponse> responseEntity = departmentServiceImplementation.deleteDepartment(department.getId());
        System.out.println(responseEntity);

        //Then
        verify(departmentRepository, never()).deleteById(department.getId());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }
}
