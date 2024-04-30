package school.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.demo.model.Section;
import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    Optional<Section> findByName(String name);
    @Query("SELECT s FROM Section s WHERE s.departmentId.id = :departmentId")
    List<Section> findByDepartmentId(Integer departmentId);
    @Query("SELECT s FROM Section s WHERE s.departmentId.name = :name")
    List<Section> findByDepartmentName(String name);
    @Query("SELECT s FROM Section s WHERE s.teacherId.id = :teacherId")
    List<Section> findByTeacherId(Integer teacherId);
    @Query("SELECT s FROM Section s WHERE s.teacherId.name = :name")
    List<Section> findByTeacherName(String name);
    @Query("SELECT s FROM Section s WHERE s.teacherId.email = :email")
    List<Section> findByTeacherEmail(String email);
}
