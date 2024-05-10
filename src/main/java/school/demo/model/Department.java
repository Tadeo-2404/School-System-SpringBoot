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
 * This class represents a Department entity.
 * It defines the properties and behavior of a department, including its name and the list of courses it offers.
 *
 * @author Tadeo Alvarez
 * @since 2024-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Department")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department(int departmentId) {
        this.id = departmentId;
    }
}
