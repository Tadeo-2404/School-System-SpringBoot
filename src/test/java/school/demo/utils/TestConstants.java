package school.demo.utils;

import lombok.experimental.UtilityClass;
import org.springframework.cache.support.NullValue;

@UtilityClass
public final class TestConstants {
    //NULL CONSTANTS
    public final NullValue NULL_VALUE = null;
    public final String NULL_STRING_VALUE = null;
    public final Integer NULL_INT_VALUE = null;

    //DEPARTMENT CONSTANTS
    public final int DEPARTMENT_ID_NOT_EXIST = 2;
    public final String DEPARTMENT_NAME = "Department Name";
    public final String DEPARTMENT_NAME_NOT_EXIST = "Department Name Not Exist";

    //COURSE CONSTANTS
    public final int COURSE_ID_NOT_EXIST = 2;
    public final String COURSE_NAME = "Course Name";
    public final String COURSE_NAME_NOT_EXIST = "Course Name Not Exist";


    //STUDENT CONSTANTS
    public final String STUDENT_NAME = "Student Name";
    public final String STUDENT_EMAIL = "student@email.com";
    public final String STUDENT_NAME_NOT_EXIST = "Student Name Not Exist";
    public final String STUDENT_EMAIL_NOT_EXIST = "student@emailNotExist.com";

    //TEACHER CONSTANTS
    public final String TEACHER_NAME = "Teacher Name";
    public final String TEACHER_EMAIL = "teacher@email.com";
    public final String TEACHER_NAME_NOT_EXIST = "Teacher Name Not Exist";
    public final String TEACHER_EMAIL_NOT_EXIST = "teacher@emailNotExist.com";

    //SECTION CONSTANTS
    public final String SECTION_NAME = "Section Name";
    public final String SECTION_NAME_NOT_EXIST = "Section Name Not Exist";
}
