package school.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "departmentId", nullable = false)
    private Department departmentId;

    @ManyToOne
    @JoinColumn(name = "teacherId", nullable = false)
    private Teacher teacherId;

    public Section(String name, Department department, Teacher teacher) {
        this.name = name;
        this.departmentId = department;
        this.teacherId = teacher;
    }
}
