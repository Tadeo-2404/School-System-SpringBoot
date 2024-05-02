package school.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.demo.model.Teacher;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByEmail(String email);
    List<Teacher> findByName(String teacherName);
    @Query("SELECT t FROM Teacher t WHERE t.department.id = :departmentId")
    List<Teacher> findByDepartmentId(int departmentId);
}
