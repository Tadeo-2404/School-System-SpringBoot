package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Department;
import school.demo.repository.DepartmentRepository;
import school.demo.service.DepartmentService;
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;

import java.util.*;

/**
 * This class represents a @Service to manage data for DepartmentService
 * It includes the implementation of the DepartmentService interface
 * @see school.demo.service.DepartmentService
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
@Service
public class DepartmentServiceImplementation implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImplementation(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<CustomResponse> getDepartments() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Department> list = departmentRepository.findAll();
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setData(null);
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

    public ResponseEntity<CustomResponse> getDepartmentsById(Integer id) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(id == null || id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) {
                customResponse.setData(department);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
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

    public ResponseEntity<CustomResponse> getDepartmentsByName(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> department = departmentRepository.findByName(name);
            if (department.isPresent()) {
                customResponse.setData(department);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setData(null);
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_NAME_MESSAGE, name));
                return new ResponseEntity<>(customResponse ,HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> createDepartment(String name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if (name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> departmentExists = departmentRepository.findByName(name);
            if (departmentExists.isPresent()) {
                customResponse.setMessage(MessageConstants.DUPLICATE_NAME_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            //save department
            Department departmentToSave = new Department(name);
            Department departmentSaved = departmentRepository.save(departmentToSave);

            customResponse.setData(departmentSaved);
            customResponse.setStatusMessage(HttpStatus.CREATED);
            customResponse.setStatusCode(HttpStatus.CREATED.value());
            customResponse.setMessage(MessageConstants.DEPARTMENT_CREATED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> editDepartment(Integer id, String name) {
        CustomResponse customResponse = new CustomResponse();
        Department departmentToSave = new Department();

        try {
            if(id == null || id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> existDepartmentName = departmentRepository.findByName(name);
            if(existDepartmentName.isPresent()) {
                customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_DUPLICATED_NAME_MESSAGE, name));
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            boolean existDepartment = departmentRepository.existsById(id);
            if(!existDepartment) {
                customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            departmentToSave.setId(id);
            departmentToSave.setName(name);
            Department department = departmentRepository.save(departmentToSave);
            customResponse.setData(department);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.DEPARTMENT_EDITED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            customResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> deleteDepartment(Integer id) {
        CustomResponse customResponse = new CustomResponse();

        try {
            if(id == null || id == 0) {
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(!departmentRepository.existsById(id)) {
                customResponse.setMessage(String.format(MessageConstants.DEPARTMENT_NOT_FOUND_MESSAGE, id));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            departmentRepository.deleteById(id);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.DEPARTMENT_REMOVED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            customResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
