package co.davsancamros.ricordati.dto.request.update;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateRequestDTO {

    @Min(1)
    @NotNull
    private Long taskCategoryId;

    @Size(max = 25)
    @NotBlank
    private String title;

    @Size(max = 150)
    @NotBlank
    private String description;

    @FutureOrPresent
    @NotNull
    private LocalDateTime dueDate;

    @FutureOrPresent
    private LocalDateTime completionDate;

    @NotNull
    @Min(1)
    private Long taskStatusId;

    @NotNull
    @Min(1)
    private Long taskPriorityLevelId;

    @NotNull
    @Min(1)
    private Long taskReminderTimeId;

}
