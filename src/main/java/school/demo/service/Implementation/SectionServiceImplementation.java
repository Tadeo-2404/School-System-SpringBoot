package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.*;
import school.demo.repository.*;
import school.demo.service.SectionService;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class represents a @Service to manage data for SectionService
 * It includes the implementation of the SectionService interface
 * @see school.demo.service.SectionService
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
@Service
public class SectionServiceImplementation implements SectionService {
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public SectionServiceImplementation(SectionRepository sectionRepository, DepartmentRepository departmentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<CustomResponse> getSections() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Section> list = sectionRepository.findAll();
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(MessageConstants.NO_REGISTRIES_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionById(Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == null) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Section> sectionOptional = sectionRepository.findById(id);
            if (sectionOptional.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(MessageConstants.NO_REGISTRIES_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(sectionOptional);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionByName(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            Optional<Section> sectionOptional = sectionRepository.findByName(name);
            if (sectionOptional.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_NAME_MESSAGE, name));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
            customResponse.setData(sectionOptional.get());
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionsByDepartmentId(Integer departmentId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(departmentId == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Section> list = sectionRepository.findByDepartmentId(departmentId);
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_DEPARTMENT_ID_MESSAGE, departmentId));
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionsByDepartmentName(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Section> list = sectionRepository.findByDepartmentName(name);
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(MessageConstants.NO_REGISTRIES_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionsByTeacherId(Integer teacherId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Section> list = sectionRepository.findByTeacherId(teacherId);
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_TEACHER_ID_MESSAGE, teacherId));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionsByTeacherName(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Section> list = sectionRepository.findByTeacherName(name);
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_TEACHER_NAME_MESSAGE, name));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getSectionsByTeacherEmail(String email) {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Section> list = sectionRepository.findByTeacherEmail(email);
            if (list.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_TEACHER_EMAIL_MESSAGE, email));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            customResponse.setData(list);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> createSection(String name, Integer departmentId, Integer courseId, Integer teacherId) {
        CustomResponse customResponse = new CustomResponse();

        try {
            //check if attributes are present
            if(name == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(departmentId == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(courseId == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_COURSE_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(teacherId == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_TEACHER_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            //checks if name already exists
            if(sectionRepository.findByName(name).isPresent()) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.DUPLICATE_NAME_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            //checks if Department exists based on its id
            Optional<Department> department = departmentRepository.findById(departmentId);
            if(department.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, departmentId));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            //checks if Course exists based on its id
            Optional<Course> course = courseRepository.findById(courseId);
            if(course.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_MESSAGE, courseId));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            //checks if Teacher exists based on its id
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            if(teacher.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_MESSAGE, teacherId));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            Section section = sectionRepository.save(new Section(name, department.orElse(null), course.orElse(null), teacher.orElse(null)));
            customResponse.setData(section);
            customResponse.setStatusMessage(HttpStatus.CREATED);
            customResponse.setStatusCode(HttpStatus.CREATED.value());
            customResponse.setMessage(MessageConstants.SECTION_CREATED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> editSection(Integer id, String name, Integer departmentId, Integer courseId, Integer teacherId) {
        CustomResponse customResponse = new CustomResponse();
        //if any attribute is not null it'll be assigned to this object
        Section sectionToUpdate = new Section();

        try {
            //checks if id is provided
            if(id == null || id == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            //checks if name already exists
            if(!sectionRepository.existsById(id)) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_MESSAGE, id));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            //check if department object exists
            if(departmentId != null) {
                //checks if Department exists based on its id
                Optional<Department> department = departmentRepository.findById(departmentId);
                if(department.isEmpty()) {
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, departmentId));
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
                sectionToUpdate.setDepartment(department.orElse(null));
            }

            //check if the Course object exists
            if(courseId != null) {
                //checks if Teacher exists based on its id
                Optional<Course> course = courseRepository.findById(courseId);
                if(course.isEmpty()) {
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_MESSAGE, courseId));
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
                sectionToUpdate.setCourse(course.orElse(null));
            }

            //check if teacher object exists
            if(teacherId != null) {
                //checks if Teacher exists based on its id
                Optional<Teacher> teacher = teacherRepository.findById(teacherId);
                if(teacher.isEmpty()) {
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_MESSAGE, teacherId));
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
                sectionToUpdate.setTeacher(teacher.orElse(null));
            }

            //if name is not null then assign it
            if(name != null) {
                sectionToUpdate.setName(name);
            }

            sectionToUpdate.setId(id);
            Section section = sectionRepository.save(sectionToUpdate);
            customResponse.setData(section);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.DEPARTMENT_EDITED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> deleteSection(Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == null) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (!sectionRepository.existsById(id)) {
                customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            sectionRepository.deleteById(id);
            customResponse.setMessage(MessageConstants.SECTION_REMOVED_MESSAGE);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
