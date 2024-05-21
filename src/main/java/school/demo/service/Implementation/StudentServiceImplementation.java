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
import school.demo.utils.CustomResponse;
import school.demo.utils.MessageConstants;

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

    public ResponseEntity<CustomResponse> getStudentByID(int studentId) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(studentId == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                customResponse.setData(student);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.STUDENT_NOT_FOUND_MESSAGE, studentId));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getStudentByEmail(String student_email) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(student_email == null) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_EMAIL_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            Optional<Student> optionalStudent = studentRepository.findByEmail(student_email);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                customResponse.setData(student);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.STUDENT_NOT_FOUND_EMAIL_MESSAGE, student_email));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> getStudents() {
        CustomResponse customResponse = new CustomResponse();
        try {
            List<Student> list = studentRepository.findAll();
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

    public ResponseEntity<CustomResponse> getStudentsByName(String student_name) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(student_name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Student> list = studentRepository.findByName(student_name);
            if (list.size() > 0) {
                customResponse.setData(list);
                customResponse.setStatusMessage(HttpStatus.OK);
                customResponse.setStatusCode(HttpStatus.OK.value());
                return new ResponseEntity<>(customResponse, HttpStatus.OK);
            } else {
                customResponse.setMessage(String.format(MessageConstants.STUDENT_NOT_FOUND_NAME_MESSAGE, student_name));
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> createStudent(String student_name, String student_email, List<Section> sections) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if (student_name == null) {
                customResponse.setMessage(MessageConstants.MISSING_NAME_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (student_email == null) {
                customResponse.setMessage(MessageConstants.MISSING_EMAIL_MESSAGE);
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            List<Section> list = new ArrayList<>();
            if(sections != null) {
                if (sections.isEmpty()) {
                    customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                    customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    customResponse.setMessage(MessageConstants.STUDENT_MISSING_SECTION_LIST);
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }

                //check if all sections exist
                for (Section s: sections) {
                    if(!sectionRepository.existsById(s.getId())) {
                        customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                        customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                        customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_MESSAGE, s.getId()));
                        return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                    }
                }
            }

            Student student = studentRepository.save(new Student(student_name, student_email, list));
            customResponse.setData(student);
            customResponse.setStatusMessage(HttpStatus.CREATED);
            customResponse.setStatusCode(HttpStatus.CREATED.value());
            customResponse.setMessage(MessageConstants.STUDENT_CREATED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> editStudent(int student_id, String student_name, String student_email, List<Section> sections) {
        CustomResponse customResponse = new CustomResponse();
        try {
            if(student_id == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if (student_email != null) {
                if (studentRepository.findByEmail(student_email).isPresent()) {
                    customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                    customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    customResponse.setMessage(MessageConstants.DUPLICATE_EMAIL_ATTRIBUTE_MESSAGE);
                    return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
                }
            }

            boolean existStudent = studentRepository.existsById(student_id);
            if(!existStudent) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.STUDENT_NOT_FOUND_MESSAGE, student_id));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            if(sections != null) {
                if (!sections.isEmpty()) {
                    //check if all sections exist
                    for (Section s: sections) {
                        if(!sectionRepository.existsById(s.getId())) {
                            customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                            customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                            customResponse.setMessage(String.format(MessageConstants.SECTION_NOT_FOUND_MESSAGE, s.getId()));
                            return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
                        }
                    }
                }
            }

            Student student = studentRepository.save(new Student(student_id, student_name, student_email, sections));
            customResponse.setData(student);
            customResponse.setStatusMessage(HttpStatus.OK);
            customResponse.setStatusCode(HttpStatus.OK.value());
            customResponse.setMessage(MessageConstants.STUDENT_EDITED_MESSAGE);
            return new ResponseEntity<>(customResponse, HttpStatus.OK);
        } catch (Exception e) {
            customResponse.setMessage(e.getMessage());
            customResponse.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR);
            customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(customResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<CustomResponse> deleteStudent(int student_id) {
        CustomResponse customResponse = new CustomResponse();

        try {
            if(student_id == 0) {
                customResponse.setStatusMessage(HttpStatus.BAD_REQUEST);
                customResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customResponse.setMessage(MessageConstants.MISSING_ID_ATTRIBUTE_MESSAGE);
                return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            }

            if(!studentRepository.existsById(student_id)) {
                customResponse.setStatusMessage(HttpStatus.NOT_FOUND);
                customResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                customResponse.setMessage(String.format(MessageConstants.STUDENT_NOT_FOUND_MESSAGE, student_id));
                return new ResponseEntity<>(customResponse, HttpStatus.NOT_FOUND);
            }

            studentRepository.deleteById(student_id);
            customResponse.setMessage(MessageConstants.STUDENT_REMOVED_MESSAGE);
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
