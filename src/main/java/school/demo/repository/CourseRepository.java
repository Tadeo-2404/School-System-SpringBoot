package school.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.demo.model.Course;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
    @Query("SELECT c FROM Course c WHERE c.department.id = :departmentId")
    List<Course> findByDepartmentId(int departmentId);
    @Query("SELECT c FROM Course c WHERE c.department.name = :name")
    List<Course> findByDepartmentName(String name);
}
