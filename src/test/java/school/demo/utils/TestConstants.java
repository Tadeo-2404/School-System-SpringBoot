package school.demo.utils;

import lombok.experimental.UtilityClass;
import org.springframework.cache.support.NullValue;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;

@UtilityClass
public final class TestConstants {
    //NULL CONSTANTS
    public static final NullValue NULL_VALUE = null;
    public static final String NULL_STRING_VALUE = null;
    public static final Integer NULL_INT_VALUE = null;

    //DEPARTMENT CONSTANTS
    public static final int DEPARTMENT_ID_NOT_EXIST = 2;
    public static final String DEPARTMENT_NAME = "Department Name";
    public static final String DEPARTMENT_NAME_NOT_EXIST = "Department Name Not Exist";
    public static final Department department = new Department(DEPARTMENT_NAME);

    //COURSE CONSTANTS
    public static final int COURSE_ID_NOT_EXIST = 2;
    public static final String COURSE_NAME = "Course Name";
    public static final Course course = new Course(COURSE_NAME, department);

    //STUDENT CONSTANTS
    public static final String STUDENT_NAME = "Student Name";
    public static final String STUDENT_EMAIL = "student@email.com";
    public static final String STUDENT_NAME_NOT_EXIST = "Student Name Not Exist";
    public static final String STUDENT_EMAIL_NOT_EXIST = "student@emailNotExist.com";

    //TEACHER CONSTANTS
    public static final String TEACHER_NAME = "Teacher Name";
    public static final String TEACHER_EMAIL = "teacher@email.com";
    public static final String TEACHER_NAME_NOT_EXIST = "Teacher Name Not Exist";
    public static final String TEACHER_EMAIL_NOT_EXIST = "teacher@emailNotExist.com";
    public static final Teacher teacher = new Teacher(TEACHER_NAME, TEACHER_EMAIL, department);

    //SECTION CONSTANTS
    public static final String SECTION_NAME = "Section Name";
    public static final String SECTION_NAME_NOT_EXIST = "Section Name Not Exist";
    public static final Section section = new Section(SECTION_NAME, department, course, teacher);
}
