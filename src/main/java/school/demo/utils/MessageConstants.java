package school.demo.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageConstants {
    public final String MISSING_NAME_MESSAGE = "Missing Name attribute";
    public final String DUPLICATE_NAME_ATTRIBUTE_MESSAGE = "Duplicated name attribute";
    public final String MISSING_ID_ATTRIBUTE_MESSAGE = "Missing ID attribute";

    public final String DEPARTMENT_CREATED_MESSAGE = "Department created successfully";
    public final String DEPARTMENT_DUPLICATED_NAME_MESSAGE = "Department with name '%s' already exists";
    public final String DEPARTMENT_NOT_FOUND_MESSAGE = "Department with ID '%d' not found";
    public final String DEPARTMENT_EDITED_MESSAGE = "Department edited successfully";
    public final String DEPARTMENT_REMOVED_MESSAGE = "Department removed successfully";
}