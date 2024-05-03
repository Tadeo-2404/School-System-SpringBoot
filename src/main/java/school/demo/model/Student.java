package school.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * This class represents a Student entity.
 * It defines the properties and behavior of a student, including their name, email, and enrolled sections.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "SectionEnrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id"))
    private List<Section> sections;

    public Student(String name, String email, List<Section> sections) {
        this.name = name;
        this.email = email;
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
