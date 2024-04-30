package school.demo.service.Implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Department;
import school.demo.repository.DepartmentRepository;
import school.demo.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImplementation implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImplementation(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<Object> getDepartments() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Department> list = departmentRepository.findAll();
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDepartmentsById(int id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) {
                data.put("data", department);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getDepartmentsByName(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Department> department = departmentRepository.findByName(name);
            if (department.isPresent()) {
                data.put("data", department);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createDepartment(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (name == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            } else {
                Optional<Department> departmentExists = departmentRepository.findByName(name);
                if(departmentExists.isPresent()) {
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    data.put("message", "Duplicated attribute name");
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }

                Department department = departmentRepository.save(new Department(name));
                data.put("data", department);
                data.put("statusMessage", HttpStatus.CREATED);
                data.put("statusCode", HttpStatus.CREATED.value());
                return new ResponseEntity<>(data, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editDepartment(int id, String name) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (name == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            boolean existDepartment = departmentRepository.existsById(id);
            System.out.println("existDepartment: " + existDepartment);
            if(!existDepartment) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Department with ID '" + id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            Department department = departmentRepository.save(new Department(id, name));
            data.put("data", department);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Department edited successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteDepartment(int id) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!departmentRepository.existsById(id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Department with ID '" + id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            departmentRepository.deleteById(id);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Department removed successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
