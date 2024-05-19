package school.demo.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import school.demo.model.Section;
import school.demo.utils.MessageConstants;
import school.demo.utils.TestConstants;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SectionRepositoryTest {
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public SectionRepositoryTest(SectionRepository sectionRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @BeforeEach
    public void setup() {
        departmentRepository.save(TestConstants.department);
        teacherRepository.save(TestConstants.teacher);
        courseRepository.save(TestConstants.course);
        sectionRepository.save(TestConstants.section);
    }

    @Test
    @Tag("Section_findByName")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByName_ShouldReturnCorrectEntity_WhenNameExists() {
        //Given
        String givenName = TestConstants.SECTION_NAME;

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName(givenName);

        //Then
        Assertions.assertEquals(givenName, sectionOptional.get().getName(), MessageConstants.SECTION_PRESENT_MESSAGE);
    }

    @Test
    @Tag("Section_findByName")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByName_ShouldReturnsEmptyResult_WhenNameNotExists() {
        //Given
        String name = "section001";

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName("section001");

        //Then
        Assertions.assertTrue(sectionOptional.isEmpty(), "Section is present");
    }

    @Test
    @Tag("Section_findByName")
    @Tag("Section_null")
    @Tag("Null")
    public void findByName_ShouldReturnsEmptyResult_WhenNameIsNull() {
        //Given
        String name = null;

        //When
        Optional<Section> sectionOptional = sectionRepository.findByName(name);

        //Then
        Assertions.assertTrue(sectionOptional.isEmpty(), "Section is present");
    }

    @Test
    @Tag("Section_findByDepartmentId")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByDepartmentId_ShouldReturnCorrectEntity_WhenIdExists() {
        //Given
        int givenDepartmentId = TestConstants.department.getId();

        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(givenDepartmentId);

        //Then
        Assertions.assertNotNull(sectionList, MessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByDepartmentId")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByDepartmentId_ShouldReturnEmptyEntity_WhenIdNotExists() {
        //Given
        int givenDepartmentId = TestConstants.DEPARTMENT_ID_NOT_EXIST;

        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(givenDepartmentId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByDepartmentId")
    @Tag("Section_null")
    @Tag("Null")
    public void findByDepartmentId_ShouldReturnEmptyList_WhenIdIsNull() {
        //Given
        Integer givenDepartmentId = TestConstants.NULL_INT_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByDepartmentId(givenDepartmentId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherId")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByTeacherId_ShouldReturnList_WhenIdExists() {
        //Given
        int givenId = TestConstants.teacher.getId();

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(givenId);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherId")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByTeacherId_ShouldReturnEmpty_WhenIdNoExists() {
        //Given
        Integer givenId = TestConstants.NULL_INT_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(givenId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherId")
    @Tag("Section_null")
    @Tag("Null")
    public void findByTeacherId_ShouldReturnEmpty_WhenIdIsNull() {
        //Given
        Integer givenId = TestConstants.NULL_INT_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherId(givenId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherName")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByTeacherName_ShouldReturnList_WhenNameExists() {
        //Given
        String name = TestConstants.TEACHER_NAME;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(name);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherName")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByTeacherName_ShouldReturnEmptyList_WhenNameNotExists() {
        //Given
        String name = TestConstants.TEACHER_NAME_NOT_EXIST;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(name);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherName")
    @Tag("Section_null")
    @Tag("Null")
    public void findByTeacherName_ShouldReturnEmptyList_WhenNameIsNull() {
        //Given
        String givenName = TestConstants.NULL_STRING_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherName(givenName);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByTeacherEmail_ShouldReturnList_WhenEmailExists() {
        //Given
        String givenEmail = TestConstants.TEACHER_EMAIL;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByTeacherEmail_ShouldReturnEmptyList_WhenEmailNotExists() {
        //Given
        String givenEmail = TestConstants.TEACHER_EMAIL_NOT_EXIST;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByTeacherEmail")
    @Tag("Section_null")
    @Tag("Null")
    public void findByTeacherEmail_ShouldReturnEmptyList_WhenEmailIsNull() {
        //Given
        String givenEmail = TestConstants.NULL_STRING_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByTeacherEmail(givenEmail);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByCourseId")
    @Tag("Section_exist")
    @Tag("Exist")
    public void findByCourseId_ShouldReturnList_WhenCourseIdExists() {
        //Given
        Integer givenCourseId = TestConstants.course.getId();

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(givenCourseId);

        //Then
        Assertions.assertFalse(sectionList.isEmpty(), MessageConstants.EMPTY_LIST_MESSAGE);
        Assertions.assertEquals(1, sectionList.get(0).getCourse().getId(), MessageConstants.COURSE_ID_NOT_MATCH_MESSAGE);
    }

    @Test
    @Tag("Section_findByCourseId")
    @Tag("Section_not_exist")
    @Tag("Not_Exist")
    public void findByCourseId_ShouldReturnEmptyList_WhenCourseIdNotExists() {
        //Given
        Integer givenCourseId = TestConstants.COURSE_ID_NOT_EXIST;

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(givenCourseId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @Test
    @Tag("Section_findByCourseId")
    @Tag("Section_null")
    @Tag("Null")
    public void findByCourseId_ShouldReturnEmptyList_WhenCourseIdIsNull() {
        //Given
        Integer givenCourseId = TestConstants.NULL_INT_VALUE;

        //When
        List<Section> sectionList = sectionRepository.findByCourseId(givenCourseId);

        //Then
        Assertions.assertTrue(sectionList.isEmpty(), MessageConstants.NOT_EMPTY_LIST_MESSAGE);
    }

    @AfterEach
    public void teardown() {
        departmentRepository.deleteAll();
        teacherRepository.deleteAll();
        courseRepository.deleteAll();
        sectionRepository.deleteAll();
    }
}
