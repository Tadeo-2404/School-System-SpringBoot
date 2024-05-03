package school.demo.utils;

/**
 * This record represents a SectionRequestEdit, combining identifiers and updated details for editing a section.
 * It is used for handling edit requests related to sections.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
public record SectionRequestEdit(int sectionId, String section, int departmentId, int courseId, int teacherId) {}
