package school.demo.service.Implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Department;
import school.demo.model.Teacher;
import school.demo.repository.DepartmentRepository;
import school.demo.repository.TeacherRepository;
import school.demo.service.TeacherService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TeacherServiceImplementation implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherServiceImplementation(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<Object> getTeachers() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Teacher> list = teacherRepository.findAll();
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("message", "Not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTeachersById(int id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Teacher> teacher = teacherRepository.findById(id);
            if (teacher.isPresent()) {
                data.put("data", teacher);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Teacher with ID '" + id + "' not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getTeachersByName(String teacher_name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Teacher> list = teacherRepository.findByName(teacher_name);
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

    public ResponseEntity<Object> getTeachersByEmail(String email) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Teacher> teacher = teacherRepository.findByEmail(email);
            if (teacher.isPresent()) {
                data.put("data", teacher);
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

    public ResponseEntity<Object> getTeachersByDepartmentId(Department department) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Teacher> list = teacherRepository.findByDepartmentId(department.getId());
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Teachers with departmentId '" + department.getId() + "' not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createTeacher(String name, String email, Department department) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(department == null) {
                if(department.getId() == 0) {
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    data.put("message", "Missing departmentId attribute");
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Department object");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (name == null && email == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name and Email attributes");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> departmentExist = departmentRepository.findById(department.getId());
            if(departmentExist.isEmpty()) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Department with id '" + department.getId() +"' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            Teacher teacher = teacherRepository.save(new Teacher(name, email, departmentExist.orElse(null)));
            data.put("data", teacher);
            data.put("statusMessage", HttpStatus.CREATED);
            data.put("statusCode", HttpStatus.CREATED.value());
            data.put("message", "Teacher created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editTeacher(int id, String name, String email, Department department) {
        Map<String, Object> data = new HashMap<>();
        Teacher teacher = new Teacher();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (name == null && email == null && department.getId() == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing attributes");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (name != null) {
                teacher.setName(name);
            }

            if (email != null) {
                teacher.setEmail(email);
            }

            if(department.getId() != 0) {
                Optional<Department> existDepartment = departmentRepository.findById(department.getId());
                if(existDepartment.isEmpty()) {
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    data.put("message", "Department with ID '" + department.getId() + "' not found");
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                }
                teacher.setDepartmentId(existDepartment.orElse(null));
            }

            teacher.setId(id);
            Teacher teacherEdit = teacherRepository.save(teacher);
            data.put("data", teacherEdit);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Teacher edited successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteTeacher(int id) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!teacherRepository.existsById(id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_ACCEPTABLE.value());
                data.put("message", "Teacher with ID '" + id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            teacherRepository.deleteById(id);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Teacher removed successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
