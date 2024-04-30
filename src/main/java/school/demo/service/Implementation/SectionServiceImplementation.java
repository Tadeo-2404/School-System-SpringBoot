package school.demo.service.Implementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.demo.model.Department;
import school.demo.model.Section;
import school.demo.model.Teacher;
import school.demo.repository.DepartmentRepository;
import school.demo.repository.SectionRepository;
import school.demo.repository.TeacherRepository;
import school.demo.service.SectionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionServiceImplementation implements SectionService {
    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final TeacherRepository teacherRepository;

    public SectionServiceImplementation(SectionRepository sectionRepository, DepartmentRepository departmentRepository, TeacherRepository teacherRepository) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.teacherRepository = teacherRepository;
    }

    public ResponseEntity<Object> getSections() {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Section> list = sectionRepository.findAll();
            if (list.isEmpty()) {
                data.put("message", "No data found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionById(Integer id) {
        Map<String, Object> data = new HashMap<>();
        try {
            if(id == null) {
                data.put("message", "Missing ID attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            Optional<Section> sectionOptional = sectionRepository.findById(id);
            if (sectionOptional.isEmpty()) {
                data.put("message", "Section with ID '" + id + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", sectionOptional);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionByName(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            Optional<Section> sectionOptional = sectionRepository.findByName(name);
            if (sectionOptional.isEmpty()) {
                data.put("message", "Section with Name '" + name + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", sectionOptional);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionsByDepartmentId(Integer departmentId) {
        Map<String, Object> data = new HashMap<>();
        try {
            if(departmentId == null) {
                data.put("message", "Missing ID attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            List<Section> list = sectionRepository.findByDepartmentId(departmentId);
            if (list.isEmpty()) {
                data.put("message", "Sections with departmentId '" + departmentId + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionsByDepartmentName(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Section> list = sectionRepository.findByDepartmentName(name);
            if (list.isEmpty()) {
                data.put("message", "Sections with departmentName '" + name + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionsByTeacherId(Integer teacherId) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Section> list = sectionRepository.findByTeacherId(teacherId);
            if (list.isEmpty()) {
                data.put("message", "Sections with teacherId '" + teacherId + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionsByTeacherName(String name) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Section> list = sectionRepository.findByTeacherName(name);
            if (list.isEmpty()) {
                data.put("message", "Sections with teacherName '" + name + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSectionsByTeacherEmail(String email) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<Section> list = sectionRepository.findByTeacherEmail(email);
            if (list.isEmpty()) {
                data.put("message", "Sections with teacherEmail '" + email + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }
            data.put("data", list);
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> createSection(String name, Department department, Teacher teacher) {
        Map<String, Object> data = new HashMap<>();
        try {
            //check if attributes are present
            if(name == null) {
                data.put("message", "Missing name attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(department == null) {
                data.put("message", "Missing Department object");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if(teacher == null) {
                data.put("message", "Missing Teacher object");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            //checks if name already exists
            if(sectionRepository.findByName(name).isPresent()) {
                data.put("message", "Duplicated name attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            //checks if Department exists based on its id
            if(!departmentRepository.existsById(department.getId())) {
                data.put("message", "Department with id '" + department.getId() + "' does not exist");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            //checks if Teacher exists based on its id
            if(!teacherRepository.existsById(teacher.getId())) {
                data.put("message", "Teacher with id '" + teacher.getId() + "' does not exist");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            Section section = sectionRepository.save(new Section(name, department, teacher));
            data.put("data", section);
            data.put("message", "Section created successfully");
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> editSection(Integer id, String name, Department department, Teacher teacher) {
        Map<String, Object> data = new HashMap<>();
        //if any attribute is not null it'll be assigned to this object
        Section sectionToUpdate = new Section();

        try {
            //checks if id is provided
            if(id == null || id == 0) {
                data.put("message", "Missing ID attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            //checks if name already exists
            if(!sectionRepository.existsById(id)) {
                data.put("message", "Section with ID '" + id + "' does not exist");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            //check if department object exists
            if(department != null) {
                //checks if Department exists based on its id
                if(!departmentRepository.existsById(department.getId())) {
                    data.put("message", "Department with id '" + department.getId() + "' does not exist");
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                }
                sectionToUpdate.setDepartmentId(department);
            }

            //check if teacher object exists
            if(teacher != null) {
                //checks if Teacher exists based on its id
                if(!teacherRepository.existsById(teacher.getId())) {
                    data.put("message", "Teacher with id '" + teacher.getId() + "' does not exist");
                    data.put("statusMessage", HttpStatus.NOT_FOUND);
                    data.put("statusCode", HttpStatus.NOT_FOUND.value());
                    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
                }
                sectionToUpdate.setTeacherId(teacher);
            }

            //if name is not null then assign it
            if(name != null) {
                sectionToUpdate.setName(name);
            }

            sectionToUpdate.setId(id);
            Section section = sectionRepository.save(sectionToUpdate);
            data.put("data", section);
            data.put("message", "Section updated successfully");
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteSection(Integer id) {
        Map<String, Object> data = new HashMap<>();

        try {
            if(id == null) {
                data.put("message", "Missing ID attribute");
                data.put("statusMessage", HttpStatus.BAD_REQUEST);
                data.put("statusCode", HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            if (!sectionRepository.existsById(id)) {
                data.put("message", "Section with ID '" + id + "' not found");
                data.put("statusMessage", HttpStatus.NOT_FOUND);
                data.put("statusCode", HttpStatus.NOT_FOUND.value());
                return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
            }

            sectionRepository.deleteById(id);
            data.put("message", "Section deleted successfully");
            data.put("statusMessage", HttpStatus.OK);
            data.put("statusCode", HttpStatus.OK.value());
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("message", e.getMessage());
            data.put("statusMessage", HttpStatus.INTERNAL_SERVER_ERROR);
            data.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
