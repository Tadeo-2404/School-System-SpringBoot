package school.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.repository.CourseRepository;
import school.demo.repository.DepartmentRepository;
import school.demo.service.Implementation.CourseServiceImplementation;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;
import school.demo.utils.TestMessageConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static io.qameta.allure.Allure.step;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest extends BaseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private CourseServiceImplementation courseServiceImplementation;

    private Course course;
    private Department department;

    @BeforeEach
    public void setupCourse() {
        department = new Department(1, TestConstants.DEPARTMENT_NAME);
        course = new Course(TestConstants.COURSE_ID, TestConstants.COURSE_NAME, department);
    }

    @AfterEach
    public void teardownCourse() {
        department = null;
        course = null;
    }

    @Test
    public void getCourses_ShouldReturn200_WhenRegistriesExist() {
        step("Define an array list with the expected result");
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);

        step("Mock the course repository to return the expected course list");
        when(courseRepository.findAll()).thenReturn(courseList);

        step("Call the getCourses method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourses();

        step("Verify that findAll method of course repository is called exactly once");
        verify(courseRepository, times(1)).findAll();

        step("Assert that the response status code is 200 OK");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourses_ShouldReturn404_WhenRegistriesNoExist() {
        step("Mock the course repository to return the expected empty course list");
        when(courseRepository.findAll()).thenReturn(Collections.emptyList());

        step("Call the getCourses method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourses();

        step("Verify that findAll method of course repository is called once");
        verify(courseRepository, times(1)).findAll();

        step("Assert that the response status code is 404 NOT FOUND");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn200_WhenIdExist() {
        step("Mocking an existing course by defined id");
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(course.getId());

        step("Verify that findById method of course repository is called once");
        verify(courseRepository, times(1)).findById(course.getId());

        step("Assert that the response status code is 2OO OK");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn200_WhenIdNotExist() {
        int invalidId = TestConstants.COURSE_ID_NOT_EXIST;
        step("Mocking an existing course by defined id");
        when(courseRepository.findById(invalidId)).thenReturn(Optional.empty());

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(invalidId);

        step("Verify that findById method of course repository is called once");
        verify(courseRepository, times(1)).findById(invalidId);

        step("Assert that the response status code is 404 NOT FOUND");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn400_WhenIdIsNull() {
        Integer invalidId = TestConstants.NULL_INT_VALUE;

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(invalidId);

        step("Verify that findById method of course repository is never called");
        verify(courseRepository, never()).findById(anyInt());

        step("Assert that the response status code is 400 bad request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn400_WhenIdIs0() {
        Integer invalidId = TestConstants.ZERO_INT_VALUE;

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(invalidId);

        step("Verify that findById method of course repository is never called");
        verify(courseRepository, never()).findById(anyInt());

        step("Assert that the response status code is 400 bad request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn400_WhenIdIsMinusOne() {
        Integer invalidId = -1;

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(invalidId);
        System.out.println(responseEntity);

        step("Verify that findById method of course repository is never called");
        verify(courseRepository, never()).findById(anyInt());

        step("Assert that the response status code is 400 bad request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseById_ShouldReturn400_WhenIdExceedsIntMaxValue() {
        Integer invalidId = Integer.MAX_VALUE+1;

        step("Call the getCourseById method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseById(invalidId);

        step("Verify that findById method of course repository is never called");
        verify(courseRepository, never()).findById(anyInt());

        step("Assert that the response status code is 400 bad request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseByName_ShouldReturn200_WhenNameExist() {
        step("Mocking an existing registry with given name");
        String givenName = course.getName();
        when(courseRepository.findByName(givenName)).thenReturn(Optional.of(course));

        step("Call the getCourseByName method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseByName(givenName);

        step("Verify that findByName method of course repository is called once");
        verify(courseRepository, times(1)).findByName(givenName);

        step("Assert that the response status code is 200 OK");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseByName_ShouldReturn404_WhenNameNotExist() {
        step("Mocking an existing registry with given name");
        String givenName = TestConstants.COURSE_NAME_NOT_EXIST;
        when(courseRepository.findByName(givenName)).thenReturn(Optional.empty());

        step("Call the getCourseByName method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseByName(givenName);

        step("Verify that findByName method of course repository is called once");
        verify(courseRepository, times(1)).findByName(givenName);

        step("Assert that the response status code is 404 Not found");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Assert that the message is correct");
        assertEquals(String.format(MessageConstants.COURSE_NOT_FOUND_NAME_MESSAGE, givenName), responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCourseByName_ShouldReturn400_WhenNameIsNull() {
        String givenName = TestConstants.NULL_STRING_VALUE;

        step("Call the getCourseByName method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCourseByName(givenName);

        step("Verify that findByName method of course repository is never called");
        verify(courseRepository, never()).findByName(anyString());

        step("Assert that the response status code is 400 Bad Request");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Assert that the message is correct");
        assertEquals(MessageConstants.MISSING_NAME_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentId_ShouldReturn200_WhenDepartmentExist() {
        step("Mocking an existing course with a given department id");
        Integer givenId = course.getDepartment().getId();
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseRepository.findByDepartmentId(givenId)).thenReturn(courseList);

        step("Calling getCoursesByDepartmentId method from courseServiceImplementation passing department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentId(department);

        step("Asserting that the returned status code matched the expected one");
        assertEquals(HttpStatus.OK, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned data matched the expected one");
        assertEquals(courseList, responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentId_ShouldReturn404_WhenDepartmentNotExist() {
        step("Mocking an existing course with a given department id expecting an empty list");
        Integer givenId = TestConstants.DEPARTMENT_ID_NOT_EXIST;
        List<Course> courseList = new ArrayList<>();
        Department departmentNotExist = new Department(givenId);
        when(courseRepository.findByDepartmentId(givenId)).thenReturn(courseList);

        step("Calling getCoursesByDepartmentId method from courseServiceImplementation passing department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentId(departmentNotExist);

        step("Asserting that the return status code matched the expected one");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned message matched the expected one");
        assertEquals(MessageConstants.NO_REGISTRIES_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);

        step("Asserting that the return status code matched the expected one");
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentId_ShouldReturn400_WhenDepartmentIsNull() {
        step("Calling getCoursesByDepartmentId method from courseServiceImplementation passing null department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentId(null);

        step("Asserting that the return status code matched the expected one");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned message matched the expected one");
        assertEquals(MessageConstants.MISSING_DEPARTMENT_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);

        step("Asserting that the return status code matched the expected one");
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentName_ShouldReturn200_WhenDepartmentExist() {
        step("Mocking an existing course with a given department name");
        String givenName = course.getDepartment().getName();
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseRepository.findByDepartmentName(givenName)).thenReturn(courseList);

        step("Calling getCoursesByDepartmentName method from courseServiceImplementation passing department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentName(department);

        step("Asserting that the returned status code matched the expected one");
        assertEquals(HttpStatus.OK, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned data matched the expected one");
        assertEquals(courseList, responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentName_ShouldReturn404_WhenDepartmentNotExist() {
        step("Mocking an existing course with a given department name expecting an empty list");
        String givenName = TestConstants.DEPARTMENT_NAME_NOT_EXIST;
        Department departmentNotExist = new Department(givenName);
        when(courseRepository.findByDepartmentName(givenName)).thenReturn(Collections.emptyList());

        step("Calling getCoursesByDepartmentName method from courseServiceImplementation passing department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentName(departmentNotExist);

        step("Asserting that the return status code matched the expected one");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned message matched the expected one");
        assertEquals(String.format(MessageConstants.COURSE_NOT_FOUND_DEPARTMENT_NAME, givenName), responseEntity.getBody().getMessage(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);

        step("Asserting that the return status code matched the expected one");
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
    }

    @Test
    public void getCoursesByDepartmentName_ShouldReturn400_WhenDepartmentIsNull() {
        step("Calling getCoursesByDepartmentName method from courseServiceImplementation passing null department object");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.getCoursesByDepartmentName(null);

        step("Asserting that the return status code matched the expected one");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getBody().getStatusMessage(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting that the returned message matched the expected one");
        assertEquals(MessageConstants.MISSING_DEPARTMENT_MESSAGE, responseEntity.getBody().getMessage(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);

        step("Asserting that the return status code matched the expected one");
        assertNull(responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE);
    }

    @Test
    public void createCourse_ShouldReturn200_WhenCourseCreated() {
        String givenName = TestConstants.COURSE_NAME;
        Department departmentToSave = new Department(TestConstants.DEPARTMENT_ID, TestConstants.DEPARTMENT_NAME);
        Course expectedCourse = new Course(givenName, departmentToSave);

        step("Mocking a not existing course with a given certain name");
        when(courseRepository.findByName(givenName)).thenReturn(Optional.empty());

        step("Mocking an existing department with a given id");
        when(departmentRepository.findById(departmentToSave.getId())).thenReturn(Optional.of(departmentToSave));

        step("Mocking expected course so it is returned when saved");
        when(courseRepository.save(expectedCourse)).thenReturn(expectedCourse);

        step("Calling createCourse method from courseServiceImplementation");
        ResponseEntity<CustomResponse> responseEntity = courseServiceImplementation.createCourse(givenName, departmentToSave);
        System.out.println(responseEntity);

        step("Verify that the methods were called once");
        verify(courseRepository, times(1)).findByName(anyString());
        verify(departmentRepository, times(1)).findById(anyInt());
        verify(courseRepository, times(1)).save(any(Course.class));

        step("Asserting the returned status code matches the expected one");
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), TestMessageConstants.CODE_STATUS_NOT_MATCH_MESSAGE);

        step("Asserting the returned data matches the expected one");
        assertEquals(expectedCourse, responseEntity.getBody().getData(), TestMessageConstants.DATA_ATTRIBUTE_NOT_MATCH_MESSAGE);
    }
}
