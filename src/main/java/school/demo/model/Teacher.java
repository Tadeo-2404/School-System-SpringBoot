package school.demo.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * This class represents a Teacher entity.
 * It defines the properties and behavior of a teacher, including their name, email, department, and sections taught.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Teacher")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Teacher(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Teacher(int teacherId) {
        this.id = teacherId;
    }
}
