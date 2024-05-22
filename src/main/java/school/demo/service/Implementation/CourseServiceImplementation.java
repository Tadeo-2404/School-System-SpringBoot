package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.repository.CourseRepository;
import school.demo.repository.DepartmentRepository;
import school.demo.service.CourseService;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * This class represents a @Service to manage data for CourseService
 * It includes the implementation of the CourseService interface
 * @see school.demo.service.CourseService
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
@Service
public class CourseServiceImplementation implements CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseServiceImplementation(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<CustomResponse> getCourses() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Course> list = courseRepository.findAll();
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(MessageConstants.NO_REGISTRIES_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getCourseById(int id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Course> course = courseRepository.findById(id);
            if (course.isPresent()) {
                customResponse.setData(course);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getCourseByName(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(name == null) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Course> course = courseRepository.findByName(name);
            if (course.isPresent()) {
                customResponse.setData(course);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_NAME_MESSAGE, name));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getCoursesByDepartmentId(Department department) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(department == null) {
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Course> list = courseRepository.findByDepartmentId(department.getId());
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(MessageConstants.NO_REGISTRIES_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getCoursesByDepartmentName(Department department) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(department == null) {
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Course> list = courseRepository.findByDepartmentName(department.getName());
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_DEPARTMENT_NAME, department.getName()));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> createCourse(String name, Department department) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(name != null) {
                Optional<Course> courseExists = courseRepository.findByName(name);
                if(courseExists.isPresent()) {
                    customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                    customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    customResponse.setMessage(MessageConstants.DUPLICATE_NAME_ATTRIBUTE_MESSAGE);
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }
            }

            if(department == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(department.getId() == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (name == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> departmentExist = departmentRepository.findById(department.getId());
            if(departmentExist.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(String.format(MessageConstants.MISSING_DEPARTMENT_ID_ATTRIBUTE_MESSAGE, department.getId()));
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Course course = courseRepository.save(new Course(name, departmentExist.orElse(null)));
            customResponse.setData(course);
            customResponse.setStatusMessage(HttpStatus.CREATED);
            customResponse.setStatusCode(HttpStatus.CREATED.value());
            customResponse.setMessage(MessageConstants.COURSE_CREATED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> editCourse(int id, String name, Department department) {
        CustomResponse customResponse = new CustomResponse();
        Course course = new Course();

        try {
            if(id == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Course> courseOptional = courseRepository.findById(id);
            if(courseOptional.isEmpty()) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_MESSAGE, id));
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(department != null) {
                if(department.getId() == 0) {
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_ID_ATTRIBUTE_MESSAGE);
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }

                Optional<Department> existDepartment = departmentRepository.findById(department.getId());
                if(existDepartment.isEmpty()) {
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, department.getId()));
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
                course.setDepartment(existDepartment.orElse(null));
            } else {
                course.setDepartment(courseOptional.get().getDepartment());
            }

            if(!name.isEmpty()) {
                course.setName(name);
            } else {
                course.setName(courseOptional.get().getName());
            }

            course.setId(id);
            Course courseEdit = courseRepository.save(course);
            customResponse.setData(courseEdit);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.COURSE_EDITED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> deleteCourse(int id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(!courseRepository.existsById(id)) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.COURSE_NOT_FOUND_MESSAGE, id));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            courseRepository.deleteById(id);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setData(MessageConstants.COURSE_REMOVED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
