package co.davsancamros.ricordati.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {

    private Long id;
    private TaskCategoryResponseDTO taskCategory;
    private String title;
    private String description;
    private LocalDate creationDate;
    private LocalDateTime dueDate;
    private LocalDateTime completionDate;
    private TaskStatusResponseDTO taskStatus;
    private TaskPriorityLevelResponseDTO taskPriorityLevel;
    private TaskReminderTimeResponseDTO taskReminderTime;
    private UserResponseDTO user;

}
