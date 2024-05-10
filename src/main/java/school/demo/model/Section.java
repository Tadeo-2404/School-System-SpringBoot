package school.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * This class represents a Section entity.
 * It defines the properties and behavior of a section, including its name, department, course, teacher, and enrolled students.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Section(String name, Department department, Course course, Teacher teacher) {
        this.name = name;
        this.department = department;
        this.course = course;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
