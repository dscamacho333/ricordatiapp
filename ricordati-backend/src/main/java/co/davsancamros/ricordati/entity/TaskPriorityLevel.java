package co.davsancamros.ricordati.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TASK_PRIORITY_LEVEL")
@NoArgsConstructor
@Getter
@Setter
public class TaskPriorityLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false )
    private Long id;

    @Column(name = "name", length = 25 ,nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
