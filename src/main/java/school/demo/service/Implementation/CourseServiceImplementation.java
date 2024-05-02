package school.demo.service.Implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Course;
import school.demo.model.Department;
import school.demo.repository.CourseRepository;
import school.demo.repository.DepartmentRepository;
import school.demo.service.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseServiceImplementation(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<Object> getCourses() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Course> list = courseRepository.findAll();
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

    public ResponseEntity<Object> getCourseById(int id) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Course> course = courseRepository.findById(id);
            if (course.isPresent()) {
                data.put("data", course);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Course with ID '" + id + "' not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getCourseByName(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Course> course = courseRepository.findByName(name);
            if (course.isPresent()) {
                data.put("data", course);
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
        }    }

    public ResponseEntity<Object> getCoursesByDepartmentId(Department department) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Course> list = courseRepository.findByDepartmentId(department.getId());
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Courses with departmentId '" + department.getId() + "' not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getCoursesByDepartmentName(Department department) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Course> list = courseRepository.findByDepartmentName(department.getName());
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Courses with name '" + department.getName() + "' not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createCourse(String name, Department department) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(name != null) {
                Optional<Course> courseExists = courseRepository.findByName(name);
                if(courseExists.isPresent()) {
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    data.put("message", "Duplicated name attribute");
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }
            }

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

            if (name == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing Name attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            Optional<Department> departmentExist = departmentRepository.findById(department.getId());
            if(departmentExist.isEmpty()) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Department with id '" + department.getId() +"' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            Course course = courseRepository.save(new Course(name, departmentExist.orElse(null)));
            data.put("data", course);
            data.put("statusMessage", HttpStatus.CREATED);
            data.put("statusCode", HttpStatus.CREATED.value());
            data.put("message", "course created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editCourse(int id, String name, Department department) {
        Map<String, Object> data = new HashMap<>();
        Course course = new Course();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!courseRepository.existsById(id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Course with ID '" + course + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            if (name == null && department.getId() == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing attributes");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(department.getId() != 0) {
                Optional<Department> existDepartment = departmentRepository.findById(department.getId());
                if(existDepartment.isEmpty()) {
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    data.put("message", "Department with ID '" + department.getId() + "' not found");
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                }
                course.setDepartment(existDepartment.orElse(null));
            }

            course.setName(name);
            course.setId(id);
            Course courseEdit = courseRepository.save(course);
            data.put("data", courseEdit);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Course edited successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteCourse(int id) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!courseRepository.existsById(id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Course with ID '" + id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            courseRepository.deleteById(id);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Course removed successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
