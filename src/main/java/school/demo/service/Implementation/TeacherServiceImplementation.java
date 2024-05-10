package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import school.demo.repository.DepartmentRepository;
import school.demo.repository.SectionRepository;
import school.demo.repository.TeacherRepository;
import school.demo.service.TeacherService;
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
            if(department != null) {
                if(department.getId() == 0) {
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    data.put("message", "Missing departmentId attribute");
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }
            }

            if (name == null && email == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name and Email attributes");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(department != null) {
                Optional<Department> departmentExist = departmentRepository.findById(department.getId());
                if(departmentExist.isEmpty()) {
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    data.put("message", "Department with id '" + department.getId() +"' not found");
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                }
            }

            Teacher teacherToSave = new Teacher();
            teacherToSave.setName(name);
            teacherToSave.setEmail(email);
            teacherToSave.setDepartment(department);
            Teacher teacher = teacherRepository.save(teacherToSave);
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

            Optional<Teacher> teacherFound = teacherRepository.findById(id);
            if(teacherFound.isEmpty()) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Teacher with ID '" + id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            if(department != null) {
                Optional<Department> existDepartment = departmentRepository.findById(department.getId());
                if(existDepartment.isEmpty()) {
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    data.put("message", "Department with ID '" + department.getId() + "' not found");
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
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
