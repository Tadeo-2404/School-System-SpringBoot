package school.demo.service;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<Object> getDepartments();
    ResponseEntity<Object> getDepartmentsById(int id);
    ResponseEntity<Object> getDepartmentsByName(String name);
    ResponseEntity<Object> createDepartment(String name);
    ResponseEntity<Object> editDepartment(int id, String name);
    ResponseEntity<Object> deleteDepartment(int id);
}
