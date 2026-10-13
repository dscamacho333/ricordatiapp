package co.davsancamros.ricordati.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK")
@NoArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_category_id", nullable = false)
    private TaskCategory taskCategory;

    @Column(name = "title", length = 25 ,nullable = false)
    private String title;

    @Column(name = "description", length = 150 ,nullable = false)
    private String description;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @ManyToOne
    @JoinColumn(name = "task_status_id", nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "task_priority_level_id", nullable = false)
    private TaskPriorityLevel taskPriorityLevel;

    @ManyToOne
    @JoinColumn(name = "task_reminder_time_id", nullable = false)
    private TaskReminderTime taskReminderTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
