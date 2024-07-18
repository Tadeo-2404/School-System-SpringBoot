package school.demo.utils;

import lombok.experimental.UtilityClass;

import javax.swing.plaf.PanelUI;

@UtilityClass
public class TestMessageConstants {
    public final String DATA_ATTRIBUTE_MUST_NOT_BE_EMPTY_MESSAGE = "Data must not be empty";
    public final String DATA_ATTRIBUTE_MUST_BE_EMPTY_MESSAGE = "Data must be empty";
    public final String DATA_ATTRIBUTE_NOT_MATCH_MESSAGE = "Data attribute not match";
    public final String MESSAGE_ATTRIBUTE_NOT_MATCH_MESSAGE = "Message attribute does not match";
    public final String CODE_STATUS_NOT_MATCH_MESSAGE = "Code status does not match";
    public final String SAVED_OBJECT_NOT_MATCH = "Saved object does not match";
    public final String RUNTIME_EXCEPTION_MESSAGE = "Database connection error";

    //LIST MESSAGES
    public final String EMPTY_LIST_MESSAGE = "The list is empty";
    public final String NOT_EMPTY_LIST_MESSAGE = "The list is not empty";

    //DEPARTMENT MESSAGES
    public final String DEPARTMENT_IS_NULL = "Department is null";
    public final String DEPARTMENT_NOT_NULL = "Department is not null";
    public final String DEPARTMENT_OBJECT_NOT_MATCH_MESSAGE = "Returned department object does not match";

    //TEACHER MESSAGES
    public final String TEACHER_PRESENT_MESSAGE = "Teacher is present";
    public final String TEACHER_NOT_PRESENT_MESSAGE = "Teacher is not present";

    //STUDENT MESSAGES
    public final String STUDENT_IS_NULL = "Student is null";
    public final String STUDENT_NOT_NULL = "Student is not null";

    //SECTION MESSAGES
    public final String SECTION_PRESENT_MESSAGE = "Section is present";
    public final String SECTION_NOT_PRESENT_MESSAGE = "Section is not present";

    //COURSE MESSAGES
    public final String COURSE_ID_NOT_MATCH_MESSAGE = "Course ID not match";
    public final String COURSE_IS_NULL = "Course is null";
    public final String COURSE_IS_NOT_NULL = "Course is not null";
    public final String COURSE_PRESENT_MESSAGE = "Course is present";
    public final String COURSE_NOT_PRESENT_MESSAGE = "Course is not present";
    public final String COURSE_NAME_NOT_MATCH_MESSAGE = "Name does not match";


    //EMAIL MESSAGES
    public final String EMAIL_NOT_MATCH_MESSAGE = "Email does not match";
}
