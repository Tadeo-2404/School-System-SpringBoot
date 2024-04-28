package school.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false, unique = true)
    private int student_id;
    @Column(name = "student_name", nullable = false)
    private String student_name;
    @Column(name = "student_email", nullable = false, unique = true)
    private String student_email;
}
