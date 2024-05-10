package school.demo.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Section;
import school.demo.model.Student;
import school.demo.repository.SectionRepository;
import school.demo.repository.StudentRepository;
import school.demo.service.StudentService;
import java.util.*;

/**
 * This class represents a @Service to manage data for StudentService
 * It includes the implementation of the StudentService interface
 * @see school.demo.service.StudentService
 * @author Tadeo Alvarez
 * @since  2024-05-02
 * */
@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, SectionRepository sectionRepository) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
    }

    public ResponseEntity<Object> getStudentByID(int studentId) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                data.put("data", student);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
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

    public ResponseEntity<Object> getStudentByEmail(String student_email) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Student> optionalStudent = studentRepository.findByEmail(student_email);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                data.put("data", student);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
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

    public ResponseEntity<Object> getStudents() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Student> list = studentRepository.findAll();
            if (list.size() > 0) {
                data.put("data", list);
                data.put("statusMessage", HttpStatus.OK);
                data.put("statusCode", HttpStatus.OK.value());
                return new ResponseEntity<>(data, HttpStatus.OK);
            } else {
                data.put("message", "not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getStudentsByName(String student_name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Student> list = studentRepository.findByName(student_name);
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

    public ResponseEntity<Object> createStudent(String student_name, String student_email, List<Section> sections) {
        Map<String, Object> data = new HashMap<>();

        try {
            if (student_name == null) {
                data.put("message", "Missing student name attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (student_email == null) {
                data.put("message", "Missing student email attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            List<Section> list = new ArrayList<>();
            if (sections != null) {
                if (sections.isEmpty()) {
                    data.put("message", "Missing student sections list");
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }

                //check if all sections exist
                for (Section s: sections) {
                    if(!sectionRepository.existsById(s.getId())) {
                        data.put("message", "Section with id '" + s.getId() + "' does not exist");
                        data.put("statusMessage", HttpStatus.NOT_FOUND);
                        data.put("statusCode", HttpStatus.NOT_FOUND.value());
                        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                    }
                    list.add(s);
                }
            }

            Student student = studentRepository.save(new Student(student_name, student_email, list));
            data.put("data", student);
            data.put("statusMessage", HttpStatus.CREATED);
            data.put("statusCode", HttpStatus.CREATED.value());
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editStudent(int student_id, String student_name, String student_email, List<Section> sections) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(student_id == 0) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (student_email != null) {
                if (studentRepository.findByEmail(student_email).isPresent()) {
                    data.put("statusMessage", HttpStatus.BAD_REQUEST);
                    data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                    data.put("message", "Duplicate email attribute");
                    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
                }
            }

            boolean existStudent = studentRepository.existsById(student_id);
            if(!existStudent) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                data.put("message", "Students with ID '" + student_id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            if(sections != null) {
                if (!sections.isEmpty()) {
                    //check if all students exist
                    for (Section s: sections) {
                        if(!sectionRepository.existsById(s.getId())) {
                            data.put("message", "Section with id '" + s.getId() + "' does not exist");
                            data.put("statusMessage", HttpStatus.NOT_FOUND);
                            data.put("statusCode", HttpStatus.NOT_FOUND.value());
                            return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                        }
                    }
                }
            }

            Student student = studentRepository.save(new Student(student_id, student_name, student_email, sections));
            data.put("data", student);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Student edited successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteStudent(int student_id) {
        Map<String, Object> data = new HashMap<>();
        Integer studentIdInteger = student_id;

        try {
            if(studentIdInteger == null) {
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                data.put("message", "Missing ID attribute");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(!studentRepository.existsById(student_id)) {
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_ACCEPTABLE.value());
                data.put("message", "Students with ID '" + student_id + "' not found");
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            studentRepository.deleteById(student_id);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            data.put("message", "Student removed successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
