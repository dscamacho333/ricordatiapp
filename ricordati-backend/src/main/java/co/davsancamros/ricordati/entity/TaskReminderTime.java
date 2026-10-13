package co.davsancamros.ricordati.entity;

import co.davsancamros.ricordati.enums.TimeUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TASK_REMINDER_TIME")
@NoArgsConstructor
@Getter
@Setter
public class TaskReminderTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false )
    private Long id;

    @Column(name = "time", nullable = false)
    private Integer time;

    @Enumerated(EnumType.STRING)
    private TimeUnit timeUnit;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
