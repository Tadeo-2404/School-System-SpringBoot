package school.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.demo.model.Section;
import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Query("SELECT s FROM Section s WHERE s.name = :name")
    Optional<Section> findByName(String name);
    @Query("SELECT s FROM Section s WHERE s.department.id = :departmentId")
    List<Section> findByDepartmentId(Integer departmentId);
    @Query("SELECT s FROM Section s WHERE s.department.name = :name")
    List<Section> findByDepartmentName(String name);
    @Query("SELECT s FROM Section s WHERE s.teacher.id = :teacherId")
    List<Section> findByTeacherId(Integer teacherId);
    @Query("SELECT s FROM Section s WHERE s.teacher.name = :name")
    List<Section> findByTeacherName(String name);
    @Query("SELECT s FROM Section s WHERE s.teacher.email = :email")
    List<Section> findByTeacherEmail(String email);
    @Query("SELECT s FROM Section s WHERE s.course.id = :courseId")
    List<Section> findByCourseId(Integer courseId);
}
