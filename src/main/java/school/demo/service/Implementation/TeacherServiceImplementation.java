package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Department;
import school.demo.model.Teacher;
import school.demo.repository.DepartmentRepository;
import school.demo.repository.SectionRepository;
import school.demo.repository.TeacherRepository;
import school.demo.service.TeacherService;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;

import java.util.*;

/**
 * This class represents a @Service to manage data for TeacherService
 * It includes the implementation of the TeacherService interface
 * @see school.demo.service.TeacherService
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
@Service
public class TeacherServiceImplementation implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public TeacherServiceImplementation(TeacherRepository teacherRepository, DepartmentRepository departmentRepository, SectionRepository sectionRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
        this.sectionRepository = sectionRepository;
    }

    public ResponseEntity<CustomResponse> getTeachers() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Teacher> list = teacherRepository.findAll();
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

    public ResponseEntity<CustomResponse> getTeachersById(int id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Teacher> teacher = teacherRepository.findById(id);
            if (teacher.isPresent()) {
                customResponse.setData(teacher);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_MESSAGE, id));
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

    public ResponseEntity<CustomResponse> getTeachersByName(String teacher_name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(teacher_name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Teacher> list = teacherRepository.findByName(teacher_name);
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_NAME_MESSAGE, teacher_name));
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

    public ResponseEntity<CustomResponse> getTeachersByEmail(String email) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(email == null) {
                customResponse.setMessage(MessageConstants.MISSING_EMAIL_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Teacher> teacher = teacherRepository.findByEmail(email);
            if (teacher.isPresent()) {
                customResponse.setData(teacher);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_EMAIL_MESSAGE, email));
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

    public ResponseEntity<CustomResponse> getTeachersByDepartmentId(Department department) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(department == null) {
                customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Teacher> list = teacherRepository.findByDepartmentId(department.getId());
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_DEPARTMENT_ID_MESSAGE, department.getId()));
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

    public ResponseEntity<CustomResponse> createTeacher(String name, String email, Department department) {
        CustomResponse customResponse = new CustomResponse();

        try {
            if(department != null) {
                if(department.getId() == 0) {
                    customResponse.setMessage(MessageConstants.MISSING_DEPARTMENT_ID_ATTRIBUTE_MESSAGE);
                    customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                    customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }
            }

            if (name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (email == null) {
                customResponse.setMessage(MessageConstants.MISSING_EMAIL_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(department != null) {
                Optional<Department> departmentExist = departmentRepository.findById(department.getId());
                if(departmentExist.isEmpty()) {
                    customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, department.getId()));
                    customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                    customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
            }

            Teacher teacherToSave = new Teacher();
            teacherToSave.setName(name);
            teacherToSave.setEmail(email);
            teacherToSave.setDepartment(department);
            Teacher teacher = teacherRepository.save(teacherToSave);

            customResponse.setData(teacher);
            customResponse.setStatusMessage(HttpStatus.CREATED);
            customResponse.setStatusCode(HttpStatus.CREATED.value());
            customResponse.setMessage(MessageConstants.TEACHER_CREATED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> editTeacher(int id, String name, String email, Department department) {
        CustomResponse customResponse = new CustomResponse();

        try {
            if(id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (name == null && email == null && department.getId() == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ATTRIBUTES_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Teacher> teacherFound = teacherRepository.findById(id);
            if(teacherFound.isEmpty()) {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            if(department != null) {
                Optional<Department> existDepartment = departmentRepository.findById(department.getId());
                if(existDepartment.isEmpty()) {
                    customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, id));
                    customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                    customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                    return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                }
                teacherFound.get().setDepartment(existDepartment.orElse(null));
            }

            if(!name.isEmpty()) {
                teacherFound.get().setName(name);
            } else {
                teacherFound.get().setName(teacherFound.get().getName());
            }

            if(!email.isEmpty()) {
                teacherFound.get().setEmail(email);
            } else {
                teacherFound.get().setEmail(teacherFound.get().getEmail());
            }

            Teacher teacherEdit = teacherRepository.save(teacherFound.orElse(null));
            customResponse.setData(teacherEdit);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.TEACHER_EDITED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> deleteTeacher(int id) {
        CustomResponse customResponse = new CustomResponse();

        try {
            if(id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(!teacherRepository.existsById(id)) {
                customResponse.setMessage(String.format(MessageConstants.TEACHER_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            teacherRepository.deleteById(id);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.TEACHER_REMOVED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
